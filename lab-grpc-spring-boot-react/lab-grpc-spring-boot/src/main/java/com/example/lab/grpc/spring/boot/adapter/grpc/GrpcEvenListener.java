package com.example.lab.grpc.spring.boot.adapter.grpc;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.event.GrpcServerShutdownEvent;
import net.devh.boot.grpc.server.event.GrpcServerTerminatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GrpcEvenListener {

    @EventListener
    public void onServerShutdown(GrpcServerShutdownEvent ignore) {
        log.info("gRPC server shutdown.");
    }

    @EventListener
    public void onServerTerminated(GrpcServerTerminatedEvent ignore) {
        log.warn("gRPC server terminated");
    }
}
