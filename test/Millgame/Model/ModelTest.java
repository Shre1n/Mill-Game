/**
 * @author Robin Hahn
 * @version 1.0
 * @since 2023-12-12
 * Provides all necessary classes to test the Model package
 * Tests are written with JUnit
 *
 * @see org.junit
 */
package Millgame.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of Model and all included classes
 */
public class ModelTest {
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
    void CheckForPlayerWonAndMoveIsEmpty() {
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

        char[] board = new char[]{
                game.getPLAYER_1(), game.getPLAYER_1(), game.getPLAYER_1(),
                game.getEMPTY(), game.getPLAYER_2(), game.getPLAYER_1(),
                game.getPLAYER_1(), game.getPLAYER_1(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getPLAYER_2(), game.getPLAYER_2(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getPLAYER_2(),
                game.getPLAYER_1(), game.getPLAYER_1(), game.getPLAYER_1(),
                game.getEMPTY(), game.getPLAYER_2(), game.getPLAYER_2(),
        };

        assertArrayEquals(board,game.getBoard());

        assertEquals('⌗',game.getBoard()[9]);
        game.move(1, 9);//1
        assertEquals("BLACK",game.getTurn());


        game.move(17, 16);//2
        game.steal(9); // 2
        game.move(7, 15); //1
        game.move(16,17); // 2
        game.move(15, 7);//1
        game.steal(4);//1
        game.move(17, 16);//2
        game.steal(5); //2
        game.move(20, 21);//1
        game.move(16, 17);//2
        game.move(2, 3);//1
        game.move(17, 16);//2
        game.steal(19); //2
        game.move(7,15); //1
        game.move(11,10);//2
        game.move(15, 7);//1
        game.steal(10);//1
        game.move(16, 17);//2
        game.move(7, 15);//1
        game.move(17, 16);//2
        game.steal(21);//2
        game.move(15,7);//1
        game.steal(12);//1
        // Now black is in JUMP Phase
        game.move(16,17);//2
        game.move(0,1);//1
        game.move(17,16);//2
        game.steal(3);//2
        game.move(1,2);//1
        game.move(16,17);//2
        game.move(2,1);//1
        game.move(17,16);//2
        game.steal(18);//2
//        game.move(1,2);//1
//        game.move(16,17);//2
//        game.move(2,1);//1
//        game.move(17,16);//2
//        game.steal(18);//2
        // Now white is in JUMP Phase
        game.move(1,2);//1
        game.move(16,17);//2
        game.move(2,0); //1
        game.steal(17); //1





        assertTrue(game.hasPlayer1Won());
        assertTrue(game.isGameOver());
        assertFalse(game.hasPlayer2Won());

        String s = "White has won";
        assertEquals(s, game.toString());
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
    @Test
    void ThrowsMultipleExceptions_AtStealStone(){
        var game = new Model();
        game.setPlayer(0); //1
        game.setPlayer(8); //2
        game.setPlayer(1); //1
        game.setPlayer(9); //2
        game.setPlayer(2); //1
        game.steal(9); //1
        game.setPlayer(9); //2
        game.setPlayer(16);//1
        game.setPlayer(10); //2
        assertThrows(IllegalArgumentException.class,()->game.steal(8));
        assertThrows(RuntimeException.class,()->game.steal(0));
        game.steal(16);//2
        game.setPlayer(3);
        game.setPlayer(11);
        game.setPlayer(4);
        assertThrows(IllegalArgumentException.class,()->game.steal(0));
        assertThrows(RuntimeException.class,()-> game.steal(8));
    }
    @Test
    void ThrowRuntimeException_AtNoStealableStoneAtPosition(){
        var game = new Model();
        game.setPlayer(0);
        game.setPlayer(9);
        game.setPlayer(1);
        game.setPlayer(16);
        game.setPlayer(2);

        assertThrows(RuntimeException.class,() -> game.steal(23));
    }
    @Test
    void ThrowIllegalArgumentException_AtSETButMove(){
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

        assertThrows(IllegalArgumentException.class,() -> game.setPlayer(3));
    }
    @Test
    void ThrowRuntimeException_AtMoveStoneMustBeAdjacentPositions() {
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

        assertThrows(RuntimeException.class,()->game.move(1,3));


    }
    @Test
    void ThrowRuntimeException_AtMoveStoneIsOpponentStone(){
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

        assertThrows(RuntimeException.class,()->game.move(23,16));


    }
    @Test
    void CheckForGameStatePlayer1(){
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




    }
    @Test
    void CheckPlayersTurn(){
        var game = new Model();
        game.setPlayer(0);
        assertEquals("BLACK",game.getTurn());
        assertNotEquals("WHITE",game.getTurn());
    }
    @Test
    void CheckGameStates(){
        var game = new Model();
        game.setPlayer(1);
        game.setPlayer(2);

        assertEquals(GameState.SET,game.getPlayer1State());
        assertEquals(GameState.SET,game.getPlayer2State());

    }
    @Test
    void CheckBoard(){
        var game = new Model();
        game.setPlayer(0);
        char[] board = new char[]{
                game.getPLAYER_1(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
                game.getEMPTY(), game.getEMPTY(), game.getEMPTY(),
        };
        assertArrayEquals(board,game.getBoard());
        String s = board[0] + "                  " + board[1] + "                   " + board[2] + "\n";
        s += "      " + board[8] + "            " + board[9] + "            " + board[10] + "\n";
        s += "             " + board[16] + "     " + board[17] + "     " + board[18] + "\n";
        s += board[7] + "     " + board[15] + "      " + board[23] + "           " + board[19] + "     " + board[11] + "      " + board[3] + "\n";
        s += "             " + board[22] + "     " + board[21] + "     " + board[20] + "\n";
        s += "      " + board[14] + "            " + board[13] + "            " + board[12] + "\n";
        s += board[6] + "                  " + board[5] + "                   " + board[4];
        s += "\n Next Players Turn: " + "BLACK";
        s += "\n State of Player: " + "SET";
        s += "\n Available Stones Player 1: 8";
        s += "\n Available Stones Player 2: 9";
        assertEquals(s,game.toString());
    }

    @Test
    void CheckForDrawGame(){
        var game = new Model();
        game.setPlayer(0);
        game.setPlayer(8);
        game.setPlayer(1);
        game.setPlayer(9);
        game.setPlayer(2);
        game.steal(8);
        assertFalse(game.isDraw());
        game.setPlayer(8);
        game.setPlayer(3); //1
        game.setPlayer(17);
        game.setPlayer(4);
        game.steal(17);
        game.setPlayer(10);
        assertTrue(game.isDraw());
    }

}
