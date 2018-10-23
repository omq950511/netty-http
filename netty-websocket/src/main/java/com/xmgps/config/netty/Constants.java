package com.xmgps.config.netty;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final int PORT = 6120;

    public static final String CONTEXT_PATH = "/ws";

    public static List<Channel> channels = new ArrayList<Channel>();
}
