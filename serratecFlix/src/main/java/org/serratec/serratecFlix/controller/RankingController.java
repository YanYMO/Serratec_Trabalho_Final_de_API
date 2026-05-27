package org.serratec.serratecFlix.controller;

import java.util.List;

import org.serratec.serratecFlix.dto.responsedto.FilmeResponseDTO;
import org.serratec.serratecFlix.dto.responsedto.SerieResponseDTO;
import org.serratec.serratecFlix.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/filmes")
    public ResponseEntity<List<FilmeResponseDTO>> top5Filmes() {
        return ResponseEntity.ok(rankingService.top5Filmes());
    }

    @GetMapping("/series")
    public ResponseEntity<List<SerieResponseDTO>> top5Series() {
        return ResponseEntity.ok(rankingService.top5Series());
    }
}