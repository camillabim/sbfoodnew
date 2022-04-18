package br.com.softblue.bluefood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.softblue.bluefood.infrastructure.web.validator.UploadConstraint;
import br.com.softblue.bluefood.util.FileType;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "item_cardapio")
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCardapio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome n�o pode ser vazio")
	@Size(max = 50)
	private String nome;
	
	@NotBlank(message = "A categoria n�o pode ser vazia")
	@Size(max = 25)
	private String categoria;
	
	@NotBlank(message = "A descricao n�o pode ser vazio")
	@Size(max = 80)
	private String descricao;
	
	@Size(max = 50)
	private String imagem; // imagem do item do card�pio
	
	@NotNull(message = "O pre�o nao pode ser vazio")
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private Boolean destaque;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo n�o � um arquivo de imagem v�lido")
	private transient MultipartFile imagemFile;
	
	public void setImagemFileName() {
		if(getId() == null) {
			throw new IllegalStateException("O objeto precisa primeiro ser criado");
		}
		
		this.imagem = String.format("%04d-comida.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
	}

	
	
	
}
