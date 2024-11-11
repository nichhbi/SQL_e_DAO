# Nome del JAR finale
TARGET = customer_app.jar

# Classi Java
SOURCES = Customer.java CustomerDAO.java Main.java

# Classe principale
MAIN_CLASS = Main

# Percorso ai driver MySQL Connector
MYSQL_CONNECTOR = mysql-connector-j-8.0.31.jar

# Percorso per i file di output compilati
OUT_DIR = out

# Variabili per i comandi
JAVAC = javac
JAVA = java
JAR = jar
RM = rm -rf

# Comando di default per compilare e costruire il JAR
all: $(TARGET)

# Compila tutti i file .java in un'unica operazione
$(TARGET): $(SOURCES)
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -cp $(MYSQL_CONNECTOR) -d $(OUT_DIR) $(SOURCES)
	$(JAR) cfe $(TARGET) $(MAIN_CLASS) -C $(OUT_DIR) .

# Comando per pulire i file compilati e il JAR
clean:
	$(RM) $(OUT_DIR) $(TARGET)

# Comando per lanciare il jar con il classpath del connector specificato
launch:
	$(JAVA) -cp $(TARGET):$(MYSQL_CONNECTOR) $(MAIN_CLASS)
