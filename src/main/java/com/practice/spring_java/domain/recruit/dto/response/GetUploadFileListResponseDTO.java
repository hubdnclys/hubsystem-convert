package com.practice.spring_java.domain.recruit.dto.response;


import com.practice.spring_java.domain.recruit.entity.UploadFile;

public record GetUploadFileListResponseDTO(

        String originName, //파일명
        String saveName, //저장된 파일명
        String path, //경로
        String extension, //확장자
        int size
) {

    public static GetUploadFileListResponseDTO fromEntity(
            UploadFile uploadFile) {

        return new GetUploadFileListResponseDTO(
                uploadFile.getOriginName(),
                uploadFile.getSaveName(),
                uploadFile.getPath(),
                uploadFile.getExtension(),
                uploadFile.getSize()
        );
    }
}
