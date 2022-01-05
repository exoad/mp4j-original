# custom make file for java

JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) -d $(@D) $(<F)

ALL: $(wildcard $(SRC)/*.java)

default: classes

classes: $(ALL: .java=.class)

clean:
	-rm -f $(wildcard $(SRC)/*.class)
