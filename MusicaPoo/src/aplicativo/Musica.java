package aplicativo;

public class Musica {
    private String nome;
    private String genero;
    private String arquivoAudio;
    private int duracao; // Duração em segundos

    // Construtor padrão
    public Musica() {
    }

    // Construtor com parâmetros
    public Musica(String nome, String genero, String arquivoAudio, int duracao) {
        this.setNome(nome);
        this.setGenero(genero);
        this.setArquivoAudio(arquivoAudio);
        this.setDuracao(duracao);
    }

    // Getter e Setter para 'nome'
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da música não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }

    // Getter e Setter para 'genero'
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero da música não pode ser vazio ou nulo.");
        }
        this.genero = genero;
    }

    // Getter e Setter para 'arquivoAudio'
    public String getArquivoAudio() {
        return arquivoAudio;
    }

    public void setArquivoAudio(String arquivoAudio) {
        if (arquivoAudio == null || arquivoAudio.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do arquivo de áudio não pode ser vazio ou nulo.");
        }
        this.arquivoAudio = arquivoAudio;
    }

    // Getter e Setter para 'duracao'
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração da música deve ser maior que zero.");
        }
        this.duracao = duracao;
    }

    // Método toString para exibição das informações da música
    @Override
    public String toString() {
        return "Música [Nome: " + nome + ", Gênero: " + genero + ", Duração: " + duracao + " segundos, Arquivo: " + arquivoAudio + "]";
    }
}
