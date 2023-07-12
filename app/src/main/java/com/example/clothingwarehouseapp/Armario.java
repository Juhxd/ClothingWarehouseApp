package com.example.clothingwarehouseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Armario extends AppCompatActivity {

    private ImageView ivCamiseta, ivCalca, ivCalcado;
    private Button btnCamiseta, btnCalca, btnCalcado, btnAnteriorCamiseta, btnAnteriorCalca, btnAnteriorCalcado, btnProximoCamiseta, btnProximoCalca, btnProximoCalcado;
    private List<Bitmap> listaCamisetas, listaCalcas, listaCalcados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armario);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        ivCamiseta = findViewById(R.id.ivCamiseta);
        ivCalca = findViewById(R.id.ivCalca);
        ivCalcado = findViewById(R.id.ivCalcado);
        btnCamiseta = findViewById(R.id.btnTirarCamiseta);
        btnCalca = findViewById(R.id.btnTirarCalca);
        btnCalcado = findViewById(R.id.btnTirarCalcado);
        btnAnteriorCamiseta = findViewById(R.id.btnAnteriorCamiseta);
        btnAnteriorCalca = findViewById(R.id.btnAnteriorCalca);
        btnAnteriorCalcado = findViewById(R.id.btnAnteriorCalcado);
        btnProximoCamiseta = findViewById(R.id.btnProximoCamiseta);
        btnProximoCalca = findViewById(R.id.btnProximoCalca);
        btnProximoCalcado = findViewById(R.id.btnProximoCalcado);

        listaCamisetas = new ArrayList<>();
        listaCalcas = new ArrayList<>();
        listaCalcados = new ArrayList<>();

        btnCamiseta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFoto(1);
            }
        });

        btnCalca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFoto(2);
            }
        });

        btnCalcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFoto(3);
            }
        });

        btnAnteriorCamiseta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirFotoAnterior(1);
            }
        });

        btnAnteriorCalca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirFotoAnterior(2);
            }
        });

        btnAnteriorCalcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirFotoAnterior(3);
            }
        });

        btnProximoCamiseta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirProximaFoto(1);
            }
        });

        btnProximoCalca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirProximaFoto(2);
            }
        });

        btnProximoCalcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirProximaFoto(3);
            }
        });

        // Carrega as imagens salvas anteriormente, se houver
        carregarImagensSalvas();
    }

    public void tirarFoto(int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");

            switch (requestCode) {
                case 1:
                    listaCamisetas.add(imagem);
                    exibirFoto(1);
                    break;
                case 2:
                    listaCalcas.add(imagem);
                    exibirFoto(2);
                    break;
                case 3:
                    listaCalcados.add(imagem);
                    exibirFoto(3);
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void exibirFoto(int imageView) {
        Bitmap foto = null;
        switch (imageView) {
            case 1:
                if (!listaCamisetas.isEmpty()) {
                    foto = listaCamisetas.get(listaCamisetas.size() - 1);
                }
                break;
            case 2:
                if (!listaCalcas.isEmpty()) {
                    foto = listaCalcas.get(listaCalcas.size() - 1);
                }
                break;
            case 3:
                if (!listaCalcados.isEmpty()) {
                    foto = listaCalcados.get(listaCalcados.size() - 1);
                }
                break;
        }

        switch (imageView) {
            case 1:
                ivCamiseta.setImageBitmap(foto);
                break;
            case 2:
                ivCalca.setImageBitmap(foto);
                break;
            case 3:
                ivCalcado.setImageBitmap(foto);
                break;
        }
    }

    public void exibirFotoAnterior(int imageView) {
        switch (imageView) {
            case 1:
                if (!listaCamisetas.isEmpty()) {
                    int index = listaCamisetas.size() - 1;
                    Bitmap foto = listaCamisetas.remove(index);
                    listaCamisetas.add(0, foto);
                    exibirFoto(1);
                }
                break;
            case 2:
                if (!listaCalcas.isEmpty()) {
                    int index = listaCalcas.size() - 1;
                    Bitmap foto = listaCalcas.remove(index);
                    listaCalcas.add(0, foto);
                    exibirFoto(2);
                }
                break;
            case 3:
                if (!listaCalcados.isEmpty()) {
                    int index = listaCalcados.size() - 1;
                    Bitmap foto = listaCalcados.remove(index);
                    listaCalcados.add(0, foto);
                    exibirFoto(3);
                }
                break;
        }
    }

    public void exibirProximaFoto(int imageView) {
        switch (imageView) {
            case 1:
                if (!listaCamisetas.isEmpty()) {
                    Bitmap foto = listaCamisetas.remove(0);
                    listaCamisetas.add(foto);
                    exibirFoto(1);
                }
                break;
            case 2:
                if (!listaCalcas.isEmpty()) {
                    Bitmap foto = listaCalcas.remove(0);
                    listaCalcas.add(foto);
                    exibirFoto(2);
                }
                break;
            case 3:
                if (!listaCalcados.isEmpty()) {
                    Bitmap foto = listaCalcados.remove(0);
                    listaCalcados.add(foto);
                    exibirFoto(3);
                }
                break;
        }
    }

    public void salvarImagens() {
        try {
            salvarListaImagens(listaCamisetas, "camisetas.dat");
            salvarListaImagens(listaCalcas, "calças.dat");
            salvarListaImagens(listaCalcados, "calçados.dat");
            Toast.makeText(getApplicationContext(), "Imagens salvas com sucesso", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro ao salvar as imagens", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarListaImagens(List<Bitmap> listaImagens, String nomeArquivo) throws IOException {
        File file = new File(getFilesDir(), nomeArquivo);
        FileOutputStream fos = new FileOutputStream(file);
        for (Bitmap imagem : listaImagens) {
            imagem.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
        fos.close();
    }

    private void carregarImagensSalvas() {
        try {
            carregarListaImagens(listaCamisetas, "camisetas.dat");
            carregarListaImagens(listaCalcas, "calças.dat");
            carregarListaImagens(listaCalcados, "calçados.dat");
            exibirFoto(1);
            exibirFoto(2);
            exibirFoto(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarListaImagens(List<Bitmap> listaImagens, String nomeArquivo) throws IOException {
        File file = new File(getFilesDir(), nomeArquivo);
        FileInputStream fis = new FileInputStream(file);
        Bitmap imagem;
        while ((imagem = BitmapFactory.decodeStream(fis)) != null) {
            listaImagens.add(imagem);
        }
        fis.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        salvarImagens();
    }
}