SELECT C.*
FROM CLIENT C
         INNER JOIN DOSSIER D ON C.CODE_CLI = D.CODE_CLI
         INNER JOIN VEHICULE V ON V.NO_IMM = D.NO_IMM
HAVING COUNT(DISTINCT V.MODELE) = 2
GROUP BY C.CODE_CLI, NOM, RUE, VILLE, CODPOSTAL
ORDER BY C.CODE_CLI

