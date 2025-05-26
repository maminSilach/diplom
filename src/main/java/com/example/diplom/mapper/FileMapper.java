package com.example.diplom.mapper;


import com.example.diplom.dto.response.FileResponse;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper {

    FileResponse toFileResponse(String url, String key);
}
