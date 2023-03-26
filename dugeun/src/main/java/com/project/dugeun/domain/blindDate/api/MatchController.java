package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MatchController {

    private final MatchRepository matchRepository;


}
