package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    static class Cat{
        public String Name;
        public int Age;

        public Cat(String name, int age) {
            Name = name;
            Age = age;
        }

    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t)  {
            try {
                Cat cat = new Cat("Вася", 3);

                //String response = "My Name is SuperServer";
                String response = new Gson().toJson(cat);

                byte[] bs = response.getBytes("UTF-8");
                t.sendResponseHeaders(200, bs.length);

                OutputStream os = t.getResponseBody();
                os.write(bs);
                os.close();
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(34562), 0);
        server.createContext("/api/name", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("server started!");
    }


}
