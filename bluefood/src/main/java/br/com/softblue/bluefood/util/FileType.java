package br.com.softblue.bluefood.util;

public enum FileType {

	PNG("image/png", "png"),
	JPG("image/jpeg", "jpg");
	
	String mimeType; //tipo tabelado que representa cada tipo de arquivo
	String extension; // extensão do arquivo
	
	//Construtor
	private FileType(String mimeType, String extension) {
		this.mimeType = mimeType;
		this.extension = extension;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public boolean sameOf(String mimeType) {
		return this.mimeType.equalsIgnoreCase(mimeType);
	}
	
	public static FileType of(String mimeType) {
		for(FileType fileType : values()) {
			if(fileType.sameOf(mimeType)) {
				return fileType;
			}
		}
		return null;
	}
	
}
