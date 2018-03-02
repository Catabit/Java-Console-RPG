# Java-Console-RPG
Pentru rezolvarea acestei teme am optat sa modularizez cat mai mult conceptul de Hero si de Spell:
Orice Hero are 2 'slot-uri' de abilitati care pot fi ocupate de orice obiect de tip Spell, astfel
se pot obtine (ipotetic vorbind) clase hibride, de ex Battlemage cu Execute si Drain.
Fiecare particularizare a clasei Hero sau a clasei Spell are in pachetul Defaults un 'fisier de
configurare' prin care sunt setate, in mod accesibil (si de catre alte clase), constantele specifice.

Am folosit tehnica 'double dispatch' in relatiile Spell - Hero (pentru a accesa modificatorul
specific clasei) si Hero - MapTile (pentru a verifica modificatorul de teren).

Clasa DamageOverTime incapsuleaza si conceptul de cripple/incapacitate.
Flow-ul unei runde este, dupa cum urmeaza:
    - movement global al eroilor (daca nu sunt incapacitati)
    - aplicarea efectelor Overtime (damage, decrementarea counterului si, dupa caz, eliminarea acestuia)
    - se verifica eroii, 2 cate 2. Daca sunt pe aceleasi pozitii pe mapa si daca sunt ambii vii, se
vor lupta:
        - ambii eroi se vor pregati de lupta setandu-se reciproc ca tinte si isi vor calcula damage-ul
    pe care il vor da
        - se va aplica damage-ul asupra unui HP temporar (pentru a simula simultaneitatea luptei),
    care la finalul rundei va fi facut 'permanent'
        - se verifica daca cel putin un erou este mort, si se ofera xp, dupa caz.

Catalin-Constantin Bitire
325CD
