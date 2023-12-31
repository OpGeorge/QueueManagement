# QueueManagement
Prioect care simuleaza lucrul cu thread-uri in java de la sincronizare la apelare.  
Simularea se bazeaza pe ideea unui magazin care serveste clienti prin intermediul caselor.  
Casele sunt reprezentate de servere in cod, clientii de thread-uri.  
Fiecare client merge la casa in funcite de disponibilitate si timpul la care ajunge  

# Parametrii sunt:  
- Time limit (s) : simuleaza cat ar fi deschise serverele (casele de la magazin care servesc clientii).
- min - max Procesing time : (random) cat dureaza task-ul primit de la clinet sa fie indeplinit.
- min - max Arrival Time : (random) in ce interval ajunge clientul.
- noOfServers : destul de clar (nr de case).
- noOfClients : destul de clar (nr de thrad-uri).
- selectionPolicy : cum se face selectia, 1 se ia dupa timpul in care ajunge, 0 pentru cel mai mic process time (poate fi si invers).
