## Sonarlint

Sonarlint által dobott összes code smell-et és warning-ot átnéztük egyesével és ahol úgy láttuk, hogy lehetséges a javítás egyéb komplikációk nélkül ott ezt elvégeztük. (Sajnos a projekt jellege miatt sokszor nem valós hibákra hívta fel a sonarlint a figyelmünket.) Összeségében csaknem a code smellek jelentős részét sikeresen javítottuk, ezzel a projekt általános minőségét emeltük.

## SonarCloud

A szükséges jogosultságok megszerzése után a CI config-ba bekötöttük a SonarCloudot is, így minden sikeres build után elemzést futtat a projektre.
