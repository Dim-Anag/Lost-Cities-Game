JAVAC=javac
JAVA=java
SRC=src
BIN=bin

all: $(BIN)/Main.class

$(BIN)/Main.class: $(SRC)/Main.java
	mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(SRC)/*.java $(SRC)/controller/*.java $(SRC)/model/*.java $(SRC)/util/*.java $(SRC)/view/*.java

run: all
	cd src && $(JAVA) -cp ../$(BIN) Main

clean:
	rm -rf $(BIN)

.PHONY: all run clean