package br.com.softblue.bluefood.domain.restaurante;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import br.com.softblue.bluefood.infrastructure.web.validator.UploadConstraint;
import br.com.softblue.bluefood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity //jpa deve haver uma tabela no bd chamada Restaurante 
@Table(name = "restaurante")
public class Restaurante extends Usuario{

	@NotBlank(message = "O CNPJ n�o pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inv�lido")
	@Column(length = 14, nullable = false) // tamanho da varchar no banco de dados
	private String cnpj;
	
	@Size(max = 80)
	private String logotipo;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo n�o � um arquivo de imagem v�lido " )
	private transient MultipartFile logotipoFile; // tipo transient serve para n�o armazenar no banco de dados.
	
	@NotNull(message = "A taxa de entrega n�o pode ser vazia")//blank � para string
	@Min(0) // utilizado para intervalos num�ricos
	@Max(99)// utilizado para intervalos num�ricos
	private BigDecimal taxaEntrega;
	
	@NotNull(message = "O tempo de entrega n�o pode ser vazia")//blank � para string
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;
	
	//RELACIONAMENTO MUITOS PARA MUITOS
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "restaurante_has_categoria",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
	)
	@Size(min = 1, message = "O restaurante precisa ter pelo menos uma categoria")
	@ToString.Exclude
	private Set<CategoriaRestaurante> categorias = new HashSet<>(0);
	
	@OneToMany(mappedBy = "restaurante")
	private Set<ItemCardapio> itensCardapio = new HashSet<>(0);
	
	public void setLogotipoFileName() {
		
		if(getId() == null) {
			throw new IllegalStateException("� preciso primeiramente gravar o registro");
		}

		this.logotipo = String.format("%04d-logo.%s", getId(), FileType.of(logotipoFile.getContentType()).getExtension());
		
	}
}
