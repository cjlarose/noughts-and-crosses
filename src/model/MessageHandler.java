package model;

import model.IRCBot;

abstract class MessageHandler {

    IRCBot bot;

    public MessageHandler(IRCBot bot){
        this.bot = bot;
    }

    abstract void handle(String message);
}

