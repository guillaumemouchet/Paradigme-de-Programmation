#!/bin/bash
#

set -e
set -u

# -------------------------------------------
# 			Rappel
# -------------------------------------------
#
# Separateur:
#
#		Windows		;
#		Linux/Mac	: 
#
# Classpath:
#
#		Acronyme
#
# 		-cp
#	
#		Configuration
#
# 		.			=> 	met dans le cp tous       .class   du repertoire courant 
# 		./*			=> 	met dans le cp tous les    jar     du repertoire courant
#		./ext/* 	=>	met dans le cp tous les    jar	   du folder ext
# 
# Memory:
#
# 	-Xms:200m	memoire reservee par l'OS, que la jvm l'emploie ou pas!
# 	-Xmx:500m	memoire max que l'OS peut allouer au processus
#
# 	-verbose:gc		=> utile pour optimisation, affiche l'activite du garbage collector  (Warning: ne pas livrer chez le client)
#
# Assertion:
#
#		-ea  	enable assertion
#		-da		disable assertion
#
# System Properties :
#	
#		-Da=1 -Dc=3 -Db=3.14
#

# -------------------------------------------
# 			Linux specific
# -------------------------------------------

# Warning:
#
#		(W1) Lisez ../linux/installJava.sh
#
#		(W2) Lisez ../linux/tips.txt
#

# -------------------------------------------
# 			Debug
# -------------------------------------------

#java -version
#echo ""

# -------------------------------------------
# 			Run
# -------------------------------------------

#version light
java -cp .:./*:./ext/* ch.hearc.cours_01.kitbase.hello.UseHello

# version avec options
# java -cp .:./*:./ext/* -Xmx20m -Xms10m -verbose:gc -ea ch.hearc.cours_01.kitbase.hello.UseHello

# -------------------------------------------
# 			end
# -------------------------------------------

echo ""
