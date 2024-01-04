package Millgame.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Test
    void CheckMain(){
        var game = new Model();
        game.main(new String[]{});
    }
    @Test
    void ShouldStartANewGame() {
        var game = new Model();
        assertEquals('⌗', game.getEMPTY());
    }
    @Test
    void ThrowsRuntimeException_AtFieldAlreadyTaken() {
        var game = new Model();
        game.setPlayer(0);
        assertThrows(RuntimeException.class, () -> game.setPlayer(0));
    }
    @Test
    void ThrowsIndexOutOfBoundsException_AtUnknownFieldEntry(){
        var game = new Model();
        assertThrows(IndexOutOfBoundsException.class,() -> game.setPlayer(100));
    }
    @Test
    void ThrowRuntimeException_AtUnknownUseOfMethod(){
        var game = new Model();
        game.setPlayer(0); // 1
        game.setPlayer(8); //2
        game.setPlayer(1); //1
        game.setPlayer(23); //2
        game.setPlayer(2); //1
        game.steal(23); //1
        game.setPlayer(4); //2
        game.setPlayer(5);//1
        game.setPlayer(3); //2
        game.setPlayer(7);//1
        game.setPlayer(22);//2
        game.setPlayer(6);//1
        game.steal(8);//1
        game.setPlayer(17);//2
        game.setPlayer(18);//1
        game.setPlayer(11);//2
        game.setPlayer(19);//1
        game.setPlayer(12);//2
        game.setPlayer(20);//1
        game.steal(3);//1
        game.setPlayer(23);//2

        assertThrows(RuntimeException.class,() -> game.setPlayer(2));
    }
    @Test
    void CheckIfPlayer1HasWon() {
        var game = new Model();
        game.setPlayer(0); // 1
        game.setPlayer(8); //2
        game.setPlayer(1); //1
        game.setPlayer(23); //2
        game.setPlayer(2); //1
        game.steal(23); //1
        game.setPlayer(4); //2
        game.setPlayer(5);//1
        game.setPlayer(3); //2
        game.setPlayer(7);//1
        game.setPlayer(22);//2
        game.setPlayer(6);//1
        game.steal(8);//1
        game.setPlayer(17);//2
        game.setPlayer(18);//1
        game.setPlayer(11);//2
        game.setPlayer(19);//1
        game.setPlayer(12);//2
        game.setPlayer(20);//1
        game.steal(3);//1
        game.setPlayer(23);//2

        game.move(1, 9);//1
        game.move(22, 21);//2
        game.move(9, 1); //1
        game.steal(17); //1
        game.move(21, 22);//2
        game.move(1, 9);//1
        game.move(22, 21);//2
        game.move(9, 1);//1
        game.steal(11);//1
        game.move(21, 22);//2
        game.move(1, 9);//1
        game.move(22, 21);//2
        game.move(9, 1);//1
        game.steal(4);//1
        game.move(21, 10);//2
        game.move(1, 9);//1
        game.move(10, 21);//2
        game.move(9, 1);//1
        game.steal(21);//1


        assertTrue(game.hasPlayer1Won());
        assertTrue(game.isGameOver());
    }
    @Test
    void ShouldStealOpponentPlayersStone() {
        var game = new Model();
        game.setPlayer(0);//1
        game.setPlayer(8);//2
        game.setPlayer(1);//1
        game.setPlayer(23);//2
        game.setPlayer(2);//1
        game.steal(23);//1

        char[] board = new char[]{
                game.getPLAYER_1(), game.getPLAYER_1(), game.getPLAYER_1(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getPLAYER_2(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
        };


        assertArrayEquals(board, game.getBoard());
    }
    @Test
    void ShouldPlacePlayer1AtPositionOne(){
        var game = new Model();
        game.setPlayer(1);

        char[] board = new char[]{
                game.getEMPTY(), game.getPLAYER_1(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
        };

        assertArrayEquals(board, game.getBoard());

    }
    @Test
    void CheckForValidMove(){
        var game = new Model();
        game.setPlayer(0); // 1
        game.setPlayer(8); //2
        game.setPlayer(1); //1
        game.setPlayer(23); //2
        game.setPlayer(2); //1
        game.steal(23); //1
        game.setPlayer(4); //2
        game.setPlayer(5);//1
        game.setPlayer(3); //2
        game.setPlayer(7);//1
        game.setPlayer(22);//2
        game.setPlayer(6);//1
        game.steal(8);//1
        game.setPlayer(17);//2
        game.setPlayer(18);//1
        game.setPlayer(11);//2
        game.setPlayer(19);//1
        game.setPlayer(12);//2
        game.setPlayer(20);//1
        game.steal(3);//1
        game.setPlayer(23);//2

        game.move(1,9);//1

        char[] board = new char[]{
                game.getPLAYER_1(), game.getEMPTY(), game.getPLAYER_1(),
                game.getEMPTY(), game.getPLAYER_2(), game.getPLAYER_1(),
                game.getPLAYER_1(), game.getPLAYER_1(), game.getEMPTY(),
                game.getPLAYER_1(), game.getEMPTY(), game.getPLAYER_2(), game.getPLAYER_2(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getPLAYER_2(),
                game.getPLAYER_1(), game.getPLAYER_1(), game.getPLAYER_1(),
                game.getEMPTY(), game.getPLAYER_2(), game.getPLAYER_2(),
        };

        assertArrayEquals(board,game.getBoard());

    }

}
