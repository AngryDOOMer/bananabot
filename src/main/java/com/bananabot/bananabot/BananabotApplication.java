package com.bananabot.bananabot;

import com.bananabot.bananabot.webclient.PrivateWebclient;
import com.bananabot.bananabot.webclient.PublicWebclient;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BananabotApplication {

    public static void main(String[] args) {
		PrivateWebclient privateWebclient = new PrivateWebclient();
		privateWebclient.call();

//		PublicWebclient publicWebclient = new PublicWebclient();
//		publicWebclient.call();
	}

}
