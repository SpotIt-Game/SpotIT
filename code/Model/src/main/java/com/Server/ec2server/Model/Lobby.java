package com.Server.ec2server.Model;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.*;





public class Lobby{

    private int id_lobby;
    private Game game_mode;
    private ArrayList<Player> players = new ArrayList<>();





    public Lobby(JsonObject json){

        this.id_lobby = json.get("id_lobby").getAsInt();
        this.game_mode = new Game(json.get("game_mode").getAsInt());
        for(JsonElement p: json.get("players").getAsJsonArray())
            this.players.add(new Player(p.getAsInt()));

    }




    public JsonObject verifyMove(JsonObject json){

        ArrayList<String> list = new ArrayList<>();
        for(JsonElement e : json.getAsJsonArray("urls")) list.add(e.getAsString());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("valid", game_mode.verifyMove(list, players.get(json.get("player").getAsInt()-1)));
        return jsonObject;

    }


    //TODO: add score logic
    public JsonObject calculateWinner(JsonObject json){

        JsonObject jsonObject = new JsonObject();
        JsonArray arr = new JsonArray();
        for(Player i: game_mode.calculateWinner(players)) arr.add(i.getId_player());
        jsonObject.add("Winners",arr);
        return jsonObject;

    }






    public int getId_Lobby(){
        return this.id_lobby;
    }

}

