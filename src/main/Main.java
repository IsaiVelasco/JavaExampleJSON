package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.ArchivoJSON;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Date fecha = new Date();
        System.out.println(fecha);
        List<ArchivoJSON>lista = new ArrayList<>();
        File fileJson = new File("src/files/filejson.json");
        if(fileJson.exists()){
            //LEYENDO JSON FILE
            try {
                FileReader fileReader = new FileReader(fileJson);
                Type type = new TypeToken<ArrayList<ArchivoJSON>>(){}.getType();
                Gson gson = new Gson();
                lista = gson.fromJson(fileReader, type);
                //MOSTRANDO Y MODIFICANDO NOMBRES
                for (ArchivoJSON item: lista
                     ) {
                    System.out.println(item.getNombre());
                    item.setNombre(item.getNombre()+"-modificado");
                }
                //CODIGO QUE MODIFICA EL MISMO ARCHIVO
                /*
                FileWriter fileWriter = new FileWriter(fileJson);
                Gson newgson = new Gson();
                newgson.toJson(lista, fileWriter);
                fileWriter.close();*/
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        //CREANDO NUEVO JSON DE RESPALDO MODIFICADO
        try {
            FileWriter fileWriter = new FileWriter(new File("src/files/filejson-"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".json"));
            Gson newgson = new Gson();
            newgson.toJson(lista, fileWriter);
            fileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
