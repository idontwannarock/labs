package com.example.lab.grpc.spring.boot.adapter.grpc;

import com.example.lab.grpc.spring.boot.protos.GreetRequest;
import com.example.lab.grpc.spring.boot.protos.GreetResponse;
import com.example.lab.grpc.spring.boot.protos.GreetServiceGrpc;
import com.example.lab.grpc.spring.boot.protos.Greeting;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class GreetServiceGateway extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        // we get the greeting object from the request (as defined in the proto file)
        Greeting greeting = request.getGreeting();
        String result = "Hello " + greeting.getFirstName() + greeting.getLastName();
        log.info(result + "! You are in the greet method or the greet service");

        // build our response where the type should be GreetResponse
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(response);// send the response
        responseObserver.onCompleted();// complete the execution
    }
}
