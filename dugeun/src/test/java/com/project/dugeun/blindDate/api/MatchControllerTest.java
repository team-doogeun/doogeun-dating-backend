package com.project.dugeun.blindDate.api;

import com.project.dugeun.domain.blindDate.api.MatchController;
import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.user.dao.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.project.dugeun.domain.user.domain.User;
@SpringJUnitConfig
@WebMvcTest(MatchController.class)
public class MatchControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    private MatchMaker matchMaker;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private MatchRepository matchRepository;
//    @InjectMocks
//    private MatchController matchController;
//
//    @BeforeEach
//    public void setUp(){
//
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(matchController).build();
//    }
//
//    @Test
//    public void testGetMatches_TwoPersonMatch_Success() throws Exception {
//        // Mock data
//
//
//
//
//
//    }


}
