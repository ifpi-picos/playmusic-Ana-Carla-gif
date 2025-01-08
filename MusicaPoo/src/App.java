import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import aplicativo.Musica;

import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Configuração inicial
        List<Musica> musicas = new ArrayList<>();
        
        // Adicionando músicas
        Musica musica1 = new Musica("Cidade Vizinha", "Sertanejo", 
            "MusicaPoo/src/assets/cancao/henriqueejulianooficial-cidade-vizinha-9f59e1e5.wav", 179);
        Musica musica2 = new Musica("Coração de aço", "Hip Hop", 
            "MusicaPoo/src/assets/cancao/hungriahiphop-coracao-de-aco-wwwpalcomp3comdjmixer-djmixeroficial-7f241d60.wav", 244);

        musicas.add(musica1);
        musicas.add(musica2);

        // Player de áudio
        AudioPlayer player = new AudioPlayer();
        try {
            player.loadAudio(musicas.get(0).getArquivoAudio());
        } catch (Exception e) {
            System.out.println("Erro ao carregar o áudio inicial: " + e.getMessage());
        }

        // Índice da música atual
        final int[] currentIndex = {0};

        // Criar os botões
        JButton playStopButton = new JButton("Tocar");
        JButton nextButton = new JButton("Próxima");
        JButton previousButton = new JButton("Anterior");

        // Ações dos botões
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!player.isPlaying()) {
                        player.playAudio();
                        playStopButton.setText("Stop");
                    } else {
                        player.stopAudio();
                        playStopButton.setText("Play");
                    }
                } catch (Exception ex) {
                    System.out.println("Erro ao executar o áudio: " + ex.getMessage());
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    currentIndex[0] = (currentIndex[0] + 1) % musicas.size();
                    player.stopAudio();
                    player.loadAudio(musicas.get(currentIndex[0]).getArquivoAudio());
                    player.playAudio();
                    playStopButton.setText("Stop");
                    System.out.println("Tocando: " + musicas.get(currentIndex[0]).getNome());
                } catch (Exception ex) {
                    System.out.println("Erro ao carregar a próxima música: " + ex.getMessage());
                }
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    currentIndex[0] = (currentIndex[0] - 1 + musicas.size()) % musicas.size();
                    player.stopAudio();
                    player.loadAudio(musicas.get(currentIndex[0]).getArquivoAudio());
                    player.playAudio();
                    playStopButton.setText("Stop");
                    System.out.println("Tocando: " + musicas.get(currentIndex[0]).getNome());
                } catch (Exception ex) {
                    System.out.println("Erro ao carregar a música anterior: " + ex.getMessage());
                }
            }
        });

        // Configuração do painel e janela
        JPanel panel = new JPanel();
        panel.add(previousButton);
        panel.add(playStopButton);
        panel.add(nextButton);

        JFrame frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(400, 100);
        frame.setVisible(true);

        // Fecha o áudio ao encerrar o programa
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (player != null) {
                player.stopAudio();
            }
        }));
    }
}
