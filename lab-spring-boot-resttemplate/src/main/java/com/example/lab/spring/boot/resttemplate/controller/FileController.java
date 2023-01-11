package com.example.lab.spring.boot.resttemplate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "file")
@RestController
public class FileController {

	private final RestTemplate restTemplate;

	@PostMapping(path = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		log.info("upload file: {}", file.getOriginalFilename());
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		// ByteArrayResource.getFilename needs to be overridden, otherwise RestTemplate POST will throw error.
		body.add("file", new ByteArrayResource(file.getBytes()) {
			@Override
			public String getFilename() {
				return file.getOriginalFilename();
			}
		});

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/file/save", HttpMethod.POST, entity, String.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping(path = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> save(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		Path filePath = Path.of(request.getServletContext().getRealPath("/temp/" + file.getOriginalFilename()));
		if (!Files.exists(filePath.getParent())) {
			Files.createDirectory(filePath.getParent());
		}
		log.info("save to file: {}", filePath.toUri());
		file.transferTo(new File(filePath.toUri()));
		return ResponseEntity.ok().build();
	}
}
