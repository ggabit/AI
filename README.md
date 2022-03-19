# AI
Algorimi de IA: HillClimbing, Simulated Annealing, Genetic Algorithm

Probleme de optimizare cu algoritmi de inspirație naturală
			
 
Probleme de optimizare discretă: Problema rucsacului

1.	Descrierea problemei

Fie n obiecte, fiecare având o valoare și o greutate. Trebuie găsit un set de obiecte din cele n, ce pot fi plasate într-un rucsac ce are o capacitate dată, astfel încât valoarea totală a obiectelor alese să fie cât mai mare.
S-au folosit ca date de test 20 de obiecte, și un rucsac de capacitate 879. Datele vor fi citite din fișier. Soluția optimă este 1025.



2.	Detalii de aplicare a algoritmilor

Obiectele au fost reprezentate prin intermediul clasei Obiect, cu un atribute pentru greutate și valoare.

Soluțiile candidat au fost reprezentate prin intermediul clasei Solutie, cu un atribut de tip BitSet. 

Am definit o clasă abstractă InstantaRucsac, în care am definit atributele ce țin de datele de test, dar și metodele comune celor doi algoritmi. 

Evaluarea unei soluții va fi realizata cu metoda evaluare(), și va reprezenta suma valorilor obiectelor ce alcătuiesc soluția respectivă.

Am stabilit ca vecin al unei soluții candidat (reprezentată printr-un BitSet), un BitSet ce diferă de cel al soluției candidat printr-un singur Bit.

a)	Algoritmul Hillclimbing
Algoritmul este implementat în metoda gasesteSolutie() a clasei HC_Rucsac ce extinde clasa abstractă InstantaRucsac. 

Voi genera random o soluție candidat, apoi, pentru 100 de iterații, obțin cel mai bun vecin al soluției candidat. Dacă acest vecin are o evaluare mai bună (mai mare) decât cea a soluției candidat, vecinul respectiv devine noua soluție candidat. 

Soluția finală este obținută la sfârșitul celor 100 de iterații.

b)	Algoritmul Simulated Annealing
Algoritmul este implementat în metoda gasesteSolutie() a clasei SA_Rucsac ce extinde clasa abstractă InstantaRucsac. 

Am stabilit temperatura de plecare 1000, iar numărul maxim de iterații 100.
Până ajung la temperatura 0, realizez câte 100 de iterații, astfel: obțin un  vecin random al soluției candidat. Dacă acest vecin are o evaluare mai bună (mai mare) decât cea a soluției candidat, vecinul respectiv devine noua soluție candidat. Dacă nu, i se mai acordă o șansă de a deveni noua soluție candidat prin funcția P. 

După cele 100 de iterații, apelez funcția g de actualizare a temperaturii :
g(T,t)= T-0.000001.

Soluția finală este obținută în momentul în care temperatura devine 0.


3.	Rezultate experimentale

a)	Algoritmul Hillclimbing
•	itMax = 100
Am obținut:
•	valoare minimă: 307
•	valoare maximă: 746
•	valoare medie: 535.8



b)	Algoritmul Simulated Annealing
•	itMax = 100
•	T = 1000
•	g(T,t)= T-0.000001
Am obținut:
•	valoare minimă: 428
•	valoare maximă: 924
•	valoare medie: 589.2



Probleme de optimizare continuă: Funcția De Jong 1

1.	Descrierea problemei

Fie funcția: 
  ,  cu n=2
Trebuie găsit x = (x1, x2) pentru care valoarea lui f1 este minimă.
Pentru n=2 funcția devine:
f1(x) = sum(xi2)         , i=1:2,   -5.12<= xi <=5.12.  
f1(x) = x12+ x22
Caut perechea (x1, x2) din [-5.12, 5.12] care minimizeaza f1, cu precizie de 6 zecimale. Soluția optimă este x = (0, 0).
2.	Detalii de aplicare a algoritmilor
Reprezentare soluții:
Soluțiile candidat au fost reprezentate prin intermediul clasei SolutieDJ, cu un atribut de tip BitSet , reprezentând concatenarea a doua bitString-uri corespunzătoare perechii (x1, x2). 
Soluții xi posibile in [-5.12, 5.12] => (5.12+5.12) *10^6  = 10.24*10^6 = 10.240.000
8.388.608 = 2^23    <   10.24*10^6   <     2^24 = 16.777.216 
=> sunt necesari 24 biti pentru xi  => sunt necesari 48 biti pentru x.
Decodificare:
Metoda Decodificare întoarce valorile perechii (x1, x2) în baza 10, primind un BitSet reprezentând concatenarea a doua bitString-uri (în baza 2) după următoarea formulă: x = s + x’ * (d - s) / (2B+1 – 1)
În cazul nostru: s = -5.12, d = 5.12, B = 23
x’ = bB*2B + bB-1*2B-1 + bB-2*2B-2 +…+ b1*21 + b0
0 <=  x’ <= 224-1  / : (224-1)
0 <=  x’ /(224-1) <= 1  /* 10.24
0 <=  x’ * 10.24 /(224-1) <= 10.24  /-5.12
-5.12 <=  x’ * 10.24 /(224-1) – 5.12 <= 5.12  
x = x’ * 10.24 / (224-1) – 5.12
Evaluarea unei soluții va fi realizata cu metoda evaluare(), și va reprezenta valoarea funcției în punctul x.

a)	Algoritmul Hillclimbing
Algoritmul este implementat în metoda gasesteSolutie() a clasei HC_DeJong.

Voi genera random o soluție candidat, apoi, pentru 100 de iterații, obțin cel mai bun vecin al soluției candidat. Dacă acest vecin are o evaluare mai bună (mai mică) decât cea a soluției candidat, vecinul respectiv devine noua soluție candidat. 

Soluția finală este obținută la sfârșitul celor 100 de iterații.

b)	Algoritmul Simulated Annealing
Algoritmul este implementat în metoda gasesteSolutie() a clasei SA_ DeJong. 

Am stabilit temperatura de plecare 1000, iar numărul maxim de iterații 100.
Până ajung la temperatura 0, realizez câte 100 de iterații, astfel: obțin un  vecin random al soluției candidat. Dacă acest vecin are o evaluare mai bună (mai mică) decât cea a soluției candidat, vecinul respectiv devine noua soluție candidat. Dacă nu, i se mai acordă o șansă de a deveni noua soluție candidat prin funcția P. 

După cele 100 de iterații, apelez funcția g de actualizare a temperaturii :
g(T,t)= T-0.000001.

Soluția finală este obținută în momentul în care temperatura devine 0.

c)	Algoritmul genetic
Algoritmul e implementat în metoda gasesteSolutie() a clasei GA_ DeJong. 

Am stabilit dimensiunea populației 50, rata de încrucișare 0.25, rata de mutație 0.01, numărul de generații 500.

Evaluarea unei soluții va fi realizata cu metoda fitness(), și va reprezenta valoarea funcției în punctul x.

Inițializez o primă populație în mod aleator, apoi timp de 500 de generații, calculez fitness-ul tuturor indivizilor din populație, dar și suma acestora, pentru a putea obține probabilitatea fiecărui individ de a fi selectat pentru a se reproduce (cu metoda selectie()).

Populației selectate îi aplic metoda mutatie(), urmând să aleg în mod aleator câte doi indivizi pentru a produce doi urmași cu metoda incrucisare().

Soluția finală este obținută în momentul în care ajung la generația 500.


3.	Rezultate experimentale

a)	Algoritmul Hillclimbing
•	itMax = 100
Am obținut:
•	valoare minimă: 0.692429
•	valoare maximă: 36.430086
•	valoare medie: 19.29868753


b)	Algoritmul Simulated Annealing
•	itMax = 100
•	T = 1000
•	g(T,t)= T-0.000001
Am obținut:
•	valoare minimă: 1.849358
•	valoare maximă: 29.179149
•	valoare medie: 12.30248734


c)	Algoritmul Genetic
•	Nr Generatii = 500
•	Nr Indivizi în generație= 50
•	Rata mutație= 0.01
•	Rata încrucișare= 0.25
Am obținut:
•	valoare minimă: 1.171244
•	valoare maximă: 2.910576
•	valoare medie: 2.1374179






