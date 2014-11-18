package model;

import model.*;
import controller.Controller;

import java.util.*;
import java.io.IOException;

public class IRCGame
{

    private static class ChatHandler extends MessageHandler {
      private Game g;
      private Player opponent;

      public ChatHandler(IRCBot bot) {
        super(bot);
        Controller c = new Controller();
        this.g = c.getCurrentGame();
	opponent = new Player(new IntermediateStrategy());
      }

      void handle(String message){
        System.out.println("message!");
        System.out.println(message);
        if(message.contains("play with me " + bot.getNick())){
            bot.sendPrivmsg(bot.getChannel(), "Okay!");
            
            this.printGame();

        } else if (message.contains("play ")) {
          int startPos = message.indexOf("play") + 5;
          String actualMessage = message.substring(startPos);
          System.out.println("actual message!");
          System.out.println(actualMessage);
          Scanner s = new Scanner(actualMessage);
          try {
            int i = s.nextInt();
            int j = s.nextInt();
            g.makeMove(i, j);
            if (!g.isFinished())
              g.makeMove(opponent.getMove(g));
          } catch (Exception e) {
            bot.sendPrivmsg(bot.getChannel(), "Illegal move");
          }

          this.printGame();
        }
      }

      void printGame() {
        String lines[] = this.g.toString().split("\n");
        for (String line: lines) {
          this.bot.sendPrivmsg(this.bot.getChannel(), line);
        }
          char current_player = g.getCurrentPlayer();
          bot.sendPrivmsg(bot.getChannel(), current_player + "'s turn");
      }
      /*
      @Override
      public void update(Observable o, Object m) {
        Game g = (Game) o;
        if (g.isFinished()) {
                System.out.println(g.getWinner() + " wins");
        } else {
                char current_player = g.getCurrentPlayer();
                System.out.println(current_player + "'s turn");
                Scanner s = new Scanner(System.in);
                int i = s.nextInt();
                int j = s.nextInt();
                try {
                        g.makeMove(i, j);
                } catch (Exception e) {
                        System.out.println("ILLEGAL MOVE");
                        this.update(o, null);
                }
        }
      }
      */
    }

    public static void main(String [] args){
        String host = "irc.freenode.net";
        String channel = "#uofa-acm";
        String nick = "tic-tac-toe-bot";
        int port = 6667;
        IRCBot bot = new IRCBot(host, channel, nick, port);

        bot.addHandler(new ChatHandler(bot));

        bot.run();

    }
}
