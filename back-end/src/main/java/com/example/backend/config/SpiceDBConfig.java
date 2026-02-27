package com.example.backend.config;

import com.authzed.api.v1.PermissionsServiceGrpc;
import com.authzed.grpcutil.BearerToken;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiceDBConfig {

    @Value("${spicedb.host}")
    private String host;

    @Value("${spicedb.port}")
    private int port;

    @Value("${spicedb.token}")
    private String token;

    @Value("${spicedb.insecure}")
    private boolean insecure;

    @Bean
    public ManagedChannel spiceDbChannel() {
        ManagedChannelBuilder<?> builder =
                ManagedChannelBuilder.forAddress(host, port);

        if (insecure) {
            builder.usePlaintext();
        } else {
            builder.useTransportSecurity();
        }

        return builder.build();
    }

    @Bean
    public PermissionsServiceGrpc.PermissionsServiceBlockingStub
    permissionsService(ManagedChannel channel) {

        BearerToken bearerToken = new BearerToken(token);

        return PermissionsServiceGrpc
                .newBlockingStub(channel)
                .withCallCredentials(bearerToken);
    }
}
