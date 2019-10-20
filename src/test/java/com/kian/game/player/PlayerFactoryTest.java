package com.kian.game.player;

import com.kian.game.enums.PlayerType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PlayerFactoryTest {

    @Test
    public void testInstanceOf_HumanPlayer() {
        PlayerFactory factory = new PlayerFactory();

        Player player = factory.getPlayer(PlayerType.HUMAN_PLAYER, "test");
        assertThat(player, instanceOf(HumanPlayer.class));
    }

    @Test
    public void testInstanceOf_MachinePlayer() {
        PlayerFactory factory = new PlayerFactory();

        Player player = factory.getPlayer(PlayerType.MACHINE_USER, null);
        assertThat(player, instanceOf(MachinePlayer.class));
    }
}
