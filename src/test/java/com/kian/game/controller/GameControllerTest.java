package com.kian.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kian.game.enums.PlayerType;
import com.kian.game.enums.RequestStatus;
import com.kian.game.mancala.Mancala;
import com.kian.game.mancala.MancalaEnvelope;
import com.kian.game.player.Player;
import com.kian.game.player.PlayerFactory;
import com.kian.game.service.GameServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameServiceImpl gameService;

    @Test
    public void testPlayGame() throws Exception {

        PlayerFactory playerFactory = new PlayerFactory();
        Player humanPlayer1 = playerFactory.getPlayer(PlayerType.HUMAN_PLAYER, "Human Player 1");
        Player humanPlayer2 = playerFactory.getPlayer(PlayerType.HUMAN_PLAYER, "Human Player 2");
        Mancala mancala = new Mancala(0, humanPlayer1, humanPlayer2);
        mancala.playGame(0);

        MancalaEnvelope mancalaEnvelope = new MancalaEnvelope(mancala, RequestStatus.OK, null);
        ObjectMapper objectMapper = new ObjectMapper();
        String mancalaAsString = objectMapper.writeValueAsString(mancalaEnvelope);

        when(gameService.playMancala(0, 0)).thenReturn(mancalaEnvelope);
        RequestBuilder request = MockMvcRequestBuilders.put("/games/0/pits/0").accept(MediaType.APPLICATION_JSON);

        String responseString = mockMvc.perform(request).andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(mancalaAsString, responseString, false);
    }
}
