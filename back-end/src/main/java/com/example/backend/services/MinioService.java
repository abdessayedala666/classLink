package com.example.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

@Service
public class MinioService {
    private final  MinioClient minioClient ;
    public MinioService(MinioClient minioClient){
        this.minioClient = minioClient;
    }

    public void uploadFile( MultipartFile file  , String bucketName , String obhjectName) throws Exception{
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build()) ;    
        if (!bucketExists){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        minioClient.putObject(PutObjectArgs.builder()
            .bucket(bucketName)
            .object(obhjectName)
            .stream(file.getInputStream() , file.getSize() , -1)
            .contentType(file.getContentType())
            .build());
    }
    public String getFileUrl(String bucketName , String objectName){
            try{ return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName) 
                    .object(objectName).build() 
            ) ;} catch (Exception e){
                throw new RuntimeException("Failed to generate file URL", e);
            }
    }


}
