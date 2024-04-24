package org.example.taskforpractive.service.impl;

import org.example.taskforpractive.dto.NewsDTO;
import org.example.taskforpractive.exceptions.NoSuchNewsException;
import org.example.taskforpractive.model.NewsModel;
import org.example.taskforpractive.repository.NewsRepository;
import org.example.taskforpractive.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private NewsRepository repository;

    @Autowired
    public NewsServiceImpl(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public NewsDTO publishNews(String heading, String content) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setHeading(heading);
        newsDTO.setContent(content);
        newsDTO.setCreated_at(LocalDateTime.now());

        return newsDTO;
    }

    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        List<NewsModel> news = repository.findAll();
        for(NewsModel New : news){
            newsDTOS.add(convertNewsToDto(New));
        }

        return newsDTOS;
    }

    @Override
    public NewsDTO getNewsById(Long id) throws NoSuchNewsException {
        NewsModel news = repository.findById(id).orElse(null);
        if(news != null){
            return convertNewsToDto(news);
        }else{
           throw new NoSuchNewsException("This news doesn't exists");
        }
    }

    @Override
    public NewsDTO convertNewsToDto(NewsModel newsModel) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(newsModel.getId());
        newsDTO.setHeading(newsModel.getHeading());
        newsDTO.setContent(newsModel.getContent());
        repository.save(newsModel);

        return newsDTO;
    }
}
