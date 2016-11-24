MF = /tmp/square15Manifest

SQUARE15 = square15.jar
SRCDIR = square15

JFLAGS = -g
JAVAC = javac -cp ./$(SRCDIR):${CLASSPATH}

.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $<

_SQUARE15_SRC = Square15Applet.java \
	Square15Frame.java \
	Square15Panel.java \
	Square15Gui.java \
	Square.java \
	SquareView.java \
	ArrowKeys.java \
	Move.java

SQUARE15_SRC = $(_SQUARE15_SRC:%=$(SRCDIR)/%)

SQUARE15_CLASSES = $(SQUARE15_SRC:.java=.class)

$(SQUARE15):	$(SQUARE15_SRC) $(SQUARE15_CLASSES)
	rm -f $(MF)
	echo "Main-Class: $(SRCDIR)/Square15Frame" > $(MF)
	jar cmf $(MF) $@ $(SRCDIR)/*.class
	rm -f $(MF)

clean:
	rm -f $(SQUARE15) $(SRCDIR)/*.class
