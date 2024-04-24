package org.example.taskforpractive.service;

import org.example.taskforpractive.dto.NewsDTO;
import org.example.taskforpractive.exceptions.NoSuchNewsException;
import org.example.taskforpractive.model.NewsModel;

import java.util.List;

public interface NewsService {
    NewsDTO publishNews(String heading, String content);
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id) throws NoSuchNewsException;
    NewsDTO convertNewsToDto(NewsModel newsModel);
}
