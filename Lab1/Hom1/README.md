Homework 1

Acest program Java rezolva problema identificarii numerelor k-reductibile intr-un interval dat [a, b].

Programul valideaza argumentele din linia de comanda si apoi gaseste toate numerele k-reductibile in intervalul [a, b].

Am folosit StringBuilder in locul operatorului de concatenare (+) pentru construirea sirurilor de rezultate in bucla care gaseste numerele k-reductibile pentru a eficientiza programul in cazul mai multor iteratii.

Functia reduceToK este responsabila pentru reducerea unui număr la k. Prin marcarea functiei reduceToK ca private static, am indicat faptul ca este o functie interna a clasei Main, utilizata doar in interiorul acestei clase si nu trebuie sa fie accesata sau modificata din afara.

Timpul de rulare al programului este afisat în nanosecunde.
