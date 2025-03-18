package com.verduleria.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService{

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "techshop-749ba.firebasestorage.app";

    //Esta es la ruta básica de este proyecto Techshop
    final String rutaSuperiorStorage = "verduleriaRMR";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    final String archivoJsonFile = "techshop-749ba-firebase-adminsdk-fbsvc-6805e868b2.json";
}