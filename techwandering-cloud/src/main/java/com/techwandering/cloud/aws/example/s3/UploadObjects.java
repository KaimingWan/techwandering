/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techwandering.cloud.aws.example.s3;

//snippet-sourcedescription:[ListObjects.java demonstrates how to list objects within an Amazon S3 bucket.]

//snippet-keyword:[Java]
//snippet-sourcesyntax:[java]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon S3]
//snippet-keyword:[listObjectsV2]
//snippet-service:[s3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
/*
   Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

   This file is licensed under the Apache License, Version 2.0 (the "License").
   You may not use this file except in compliance with the License. A copy of
   the License is located at

    http://aws.amazon.com/apache2.0/

   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied. See the License for the
   specific language governing permissions and limitations under the License.
*/

import lombok.SneakyThrows;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.*;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * List objects within an Amazon S3 bucket.
 * <p>
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class UploadObjects {

    @SneakyThrows
    public static void main(String[] args) {
        URI endpointUri = new URI("https", "automq-ko3.oss-cn-hangzhou.aliyuncs.com", null, null);

        S3Client s3 = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("x", "x")))
                .region(Region.AWS_GLOBAL)
                .endpointOverride(endpointUri)
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(false)
                        .chunkedEncodingEnabled(false)
                        .build())
                .build();

        // 生成一个文本文件 a.txt，里面字符串为 "abc"
        Path filePath = Paths.get("a.txt");
        Files.write(filePath, "abc".getBytes());

        String bucketName = "automq-ko3";
        String key = "a.txt";

        // 初始化 multipart 上传
        CreateMultipartUploadRequest multipartUploadRequest = CreateMultipartUploadRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        CreateMultipartUploadResponse response = s3.createMultipartUpload(multipartUploadRequest);
        String uploadId = response.uploadId();

        // 上传文件的部分
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(bucketName)
                .key(key)
                .uploadId(uploadId)
                .partNumber(1)
                .build();
        UploadPartResponse uploadPartResponse = s3.uploadPart(uploadPartRequest, RequestBody.fromFile(filePath));

        // 完成 multipart 上传
        CompletedPart part = CompletedPart.builder()
                .partNumber(1)
                .eTag(uploadPartResponse.eTag())
                .build();
        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
                .parts(part)
                .build();
        CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
                .bucket(bucketName)
                .key(key)
                .uploadId(uploadId)
                .multipartUpload(completedMultipartUpload)
                .build();
        s3.completeMultipartUpload(completeMultipartUploadRequest);

        System.out.println("File uploaded: " + key);
    }
}