package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class UploadFileModelo {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
