package com.example.diplom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {

    public String url;

    public String key;

    public LocalDateTime date = LocalDateTime.now();
}
