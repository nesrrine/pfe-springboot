package pfe.jwt_spring.auth;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pfe.jwt_spring.User.User;
import pfe.jwt_spring.identification.Entities.*;
import pfe.jwt_spring.identification.repository.MapRepository;
import pfe.jwt_spring.identification.services.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class authenticationController {


    private final AuthenticationService service; // Modify to use constructor injection
    private final ObservationService observationService; // Add ObservationService dependency


    @GetMapping
    public ResponseEntity<List<Observation>> getAllObservations() {
        List<Observation> observations = observationService.getAllObservations();
        return ResponseEntity.ok(observations);
    }

    @GetMapping("/observation/{id}")
    public ResponseEntity<Observation> getObservationById(@PathVariable Long id) {
        Observation observation = observationService.getObservationById(id);
        if (observation != null) {
            return ResponseEntity.ok(observation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Autowired
    private MapRepository mapRepository;
    
    @PostMapping
    public ResponseEntity<Observation> createObservation(@RequestBody Observation observation) {
        Observation createdObservation = observationService.createObservation(observation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observation> updateObservation(@PathVariable Long id, @RequestBody Observation observation) {
        Observation updatedObservation = observationService.updateObservation(id, observation);
        if (updatedObservation != null) {
            return ResponseEntity.ok(updatedObservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable Long id) {
        observationService.deleteObservation(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws MessagingException {
        User registeredUser = service.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/authenticate") // Add the correct path for authentication
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        service.activateAccount(token);
    }

    @RestController
    public class MyController {

        @GetMapping("/example")
        public String handleGetRequest() {
            // Logique de gestion des requêtes GET
            return "GET request handled successfully";
        }
    }


    @Autowired

    private DonneesGeneralesService donneesGeneralesService;

    @Autowired
    private LogementService logementService;

    @Autowired
    private CouvertureReseauxService couvertureReseauxService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private TypeReseauService typeReseauService;

    @GetMapping("/programmes")
    public ResponseEntity<List<Programme>> getAllProgrammes() {
        List<Programme> programmes = programmeService.getAllProgrammes();
        return ResponseEntity.ok(programmes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Programme> getProgrammeById(@PathVariable Long id) {
        Optional<Programme> programme = programmeService.getProgrammeById(id);
        return programme.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/programme")
    public ResponseEntity<Programme> createProgramme(@RequestBody Programme programme) {
        Programme createdProgramme = programmeService.createProgramme(programme);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProgramme);
    }

    @PutMapping("/programme/{id}")
    public ResponseEntity<Programme> updateProgramme(@PathVariable Long id, @RequestBody Programme programmeDetails) {
        Programme updatedProgramme = programmeService.updateProgramme(id, programmeDetails);
        if (updatedProgramme != null) {
            return ResponseEntity.ok(updatedProgramme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProgramme(@PathVariable Long id) {
        programmeService.deleteProgramme(id);
        return ResponseEntity.noContent().build();
    }

    // Contrôleurs pour delegation
    @GetMapping("/gouvernorats")
    public ResponseEntity<List<Gouvernorat>> getAllGouvernorats() {
        List<Gouvernorat> gouvernorats = programmeService.getAllGouvernorats();
        return ResponseEntity.ok(gouvernorats);
    }

    @GetMapping("/gouvernorats/{id}")
    public ResponseEntity<Gouvernorat> getGouvernoratById(@PathVariable Long id) {
        Optional<Gouvernorat> gouvernorat = programmeService.getGouvernoratById(id);
        return gouvernorat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/gouvernorat")
    public ResponseEntity<Gouvernorat> createGouvernorat(@RequestBody Gouvernorat gouvernorat) {
        Gouvernorat createdGouvernorat = programmeService.createGouvernorat(gouvernorat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGouvernorat);
    }

    @PutMapping("/gouvernorat/{id}")
    public ResponseEntity<Gouvernorat> updateGouvernorat(@PathVariable Long id, @RequestBody Gouvernorat gouvernoratDetails) {
        Gouvernorat updatedGouvernorat = programmeService.updateGouvernorat(id, gouvernoratDetails);
        if (updatedGouvernorat != null) {
            return ResponseEntity.ok(updatedGouvernorat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/gouvernorat/{id}")
    public ResponseEntity<Void> deleteGouvernorat(@PathVariable Long id) {
        programmeService.deleteGouvernorat(id);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/delegations")
    public ResponseEntity<List<Delegation>> getAllDelegations() {
        List<Delegation> delegations = programmeService.getAllDelegations();
        return ResponseEntity.ok(delegations);
    }

    @GetMapping("/delegations/{id}")
    public ResponseEntity<Delegation> getDelegationById(@PathVariable Long id) {
        Optional<Delegation> delegation = programmeService.getDelegationById(id);
        return delegation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/delegation")
    public ResponseEntity<Delegation> createDelegation(@RequestBody Delegation delegation) {
        Delegation createdDelegation = programmeService.createDelegation(delegation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDelegation);
    }

    @PutMapping("/delegation/{id}")
    public ResponseEntity<Delegation> updateDelegation(@PathVariable Long id, @RequestBody Delegation delegationDetails) {
        Delegation updatedDelegation = programmeService.updateDelegation(id, delegationDetails);
        if (updatedDelegation != null) {
            return ResponseEntity.ok(updatedDelegation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delegation/{id}")
    public ResponseEntity<Void> deleteDelegation(@PathVariable Long id) {
        programmeService.deleteDelegation(id);
        return ResponseEntity.noContent().build();
    }


//


    //
    @GetMapping("/communes")
    public ResponseEntity<List<Commune>> getAllCommunes() {
        List<Commune> communes = programmeService.getAllCommunes();
        return ResponseEntity.ok(communes);
    }

    @GetMapping("/communes/{id}")
    public ResponseEntity<Commune> getCommuneById(@PathVariable Long id) {
        Optional<Commune> commune = programmeService.getCommuneById(id);
        return commune.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/commune")
    public ResponseEntity<Commune> createCommune(@RequestBody Commune commune) {
        Commune createdCommune = programmeService.createCommune(commune);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommune);
    }

    @PutMapping("/commune/{id}")
    public ResponseEntity<Commune> updateCommune(@PathVariable Long id, @RequestBody Commune communeDetails) {
        Commune updatedCommune = programmeService.updateCommune(id, communeDetails);
        if (updatedCommune != null) {
            return ResponseEntity.ok(updatedCommune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("commune/{id}")
    public ResponseEntity<Void> deleteCommune(@PathVariable Long id) {
        programmeService.deleteCommune(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/quartiers")
    public ResponseEntity<List<Quartier>> getAllQuartiers() {
        List<Quartier> quartiers = programmeService.getAllQuartiers();
        return ResponseEntity.ok(quartiers);
    }

    @GetMapping("/quartiers/{id}")
    public ResponseEntity<Quartier> getQuartierById(@PathVariable Long id) {
        Optional<Quartier> quartier = programmeService.getQuartierById(id);
        return quartier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/quartier")
    public ResponseEntity<Quartier> createQuartier(@RequestBody Quartier quartier) {
        Quartier createdQuartier = programmeService.createQuartier(quartier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuartier);
    }

    @PutMapping("/quartier/{id}")
    public ResponseEntity<Quartier> updateQuartier(@PathVariable Long id, @RequestBody Quartier quartierDetails) {
        Quartier updatedQuartier = programmeService.updateQuartier(id, quartierDetails);
        if (updatedQuartier != null) {
            return ResponseEntity.ok(updatedQuartier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/quartier/{id}")
    public ResponseEntity<Void> deleteQuartier(@PathVariable Long id) {
        programmeService.deleteQuartier(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/DonneesGenerales")
    public ResponseEntity<DonneeGenerale> createDonneesGenerales(@RequestBody DonneeGenerale donneesGenerales) {
        DonneeGenerale savedDonneesGenerales = donneesGeneralesService.saveDonneeasGenerales(donneesGenerales);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDonneesGenerales);
    }
    @PostMapping("/calculate-density")
    public Double calculateDensity(@RequestBody DonneeGenerale data) {
        // Appelez le service pour effectuer le calcul de densité
        return programmeService.calculateDensity(data);
    }
    @PutMapping("/DonneesGenerales/{id}")
    public ResponseEntity<DonneeGenerale> updateDonneesGenerales(@PathVariable Long id, @RequestBody DonneeGenerale donneesGenerales) {
        DonneeGenerale updatedDonneesGenerales = donneesGeneralesService.updateDonneesGenerales(id, donneesGenerales);
        return ResponseEntity.ok(updatedDonneesGenerales);
    }

    @DeleteMapping("DonneesGenerales/{id}")
    public ResponseEntity<Void> deleteDonneesGenerales(@PathVariable Long id) {
        donneesGeneralesService.deleteDonneesGenerales(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/logements")
    public List<Logement> getAllLogements() {
        return logementService.getAllLogements();
    }

    @PostMapping("/typiques")
    public Logement createLogementTypique(@RequestBody LogementTypique logementTypique) {
        return logementService.saveLogement(logementTypique);
    }
    @GetMapping("logements/{id}")
    public ResponseEntity<Logement> getLogementById(@PathVariable("id") Long id) {
        Logement logement = logementService.getLogementById(id);
        if (logement != null) {
            return new ResponseEntity<>(logement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logement")
    public ResponseEntity<Logement> saveLogement(@RequestBody Logement logement) {
        Logement savedLogement = logementService.saveLogement(logement);
        return new ResponseEntity<>(savedLogement, HttpStatus.CREATED);
    }

    @PutMapping("/logementss/{id}")
    public ResponseEntity<Logement> updateLogement(@PathVariable Long id, @RequestBody Logement updatedLogement) {
        // Logique pour mettre à jour le logement
        return ResponseEntity.ok(updatedLogement);
    }

    @DeleteMapping("logement/{id}")
    public ResponseEntity<Void> deleteLogement(@PathVariable("id") Long id) {
        logementService.deleteLogement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/modernes")
    public Logement createLogementModerne(@RequestBody LogementModerne logementModerne) {
        return logementService.saveLogement(logementModerne);
    }






    @GetMapping("/delegations/gouvernorat/{gouvernauratId}")
    public ResponseEntity<List<Delegation>> getDelegationsByGouvernaurat(@PathVariable Long gouvernauratId) {
        List<Delegation> delegations = programmeService.getDelegationsByGouvernaurat(gouvernauratId);
        return new ResponseEntity<>(delegations, HttpStatus.OK);
    }


    @GetMapping("/delegations/{delegationId}/communes")
    public ResponseEntity<List<Commune>> getCommunesByDelegation(@PathVariable("delegationId") long delegationId) {
        List<Commune> communes = programmeService.getCommunesByDelegation(delegationId);
            return new ResponseEntity<>(communes, HttpStatus.OK);
        }
    @GetMapping("/communes/{communeId}/quartiers")
    public ResponseEntity<List<Quartier>> getQuartiersByCommune(@PathVariable("communeId") long communeId) {
        try {
            List<Quartier> quartiers = programmeService.getQuartiersByCommune(communeId);
            return new ResponseEntity<>(quartiers, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception and return an error response
            System.err.println("Error fetching quartiers by commune: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Endpoints pour TypeReseau

    @GetMapping("/types")
    public ResponseEntity<List<TypeReseau>> getAllTypeReseaux() {
        List<TypeReseau> typeReseaux = typeReseauService.getAllTypeReseaux();
        return new ResponseEntity<>(typeReseaux, HttpStatus.OK);
    }

    @PostMapping("/types")
    public ResponseEntity<TypeReseau> createTypeReseau(@RequestBody TypeReseau typeReseau) {
        TypeReseau savedTypeReseau = typeReseauService.saveOrUpdateTypeReseau(typeReseau);
        return new ResponseEntity<>(savedTypeReseau, HttpStatus.CREATED);
    }

    @GetMapping("/types/{id}")
    public ResponseEntity<TypeReseau> getTypeReseauById(@PathVariable Long id) {
        Optional<TypeReseau> typeReseau = typeReseauService.getTypeReseauById(id);
        return typeReseau.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/types/{id}")
    public ResponseEntity<Void> deleteTypeReseauById(@PathVariable Long id) {
        typeReseauService.deleteTypeReseauById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoints pour CouvertureReseaux


    @GetMapping("/couvertures")
    public ResponseEntity<List<CouvertureReseaux>> getAllCouvertureReseaux() {
        List<CouvertureReseaux> couvertureReseaux = couvertureReseauxService.getAllCouvertureReseaux();
        return new ResponseEntity<>(couvertureReseaux, HttpStatus.OK);
    }

    @PostMapping("/couvertures")
    public ResponseEntity<List<CouvertureReseaux>> createCouvertureReseaux(@RequestBody List<CouvertureReseaux> couvertureReseauxList) {
        List<CouvertureReseaux> savedCouvertureReseaux = couvertureReseauxService.saveAll(couvertureReseauxList);
        return new ResponseEntity<>(savedCouvertureReseaux, HttpStatus.CREATED);
    }

    @GetMapping("/couverture/{id}")
    public ResponseEntity<CouvertureReseaux> getCouvertureReseauById(@PathVariable Long id) {
        Optional<CouvertureReseaux> couvertureReseaux = couvertureReseauxService.getCouvertureReseauxById(id);
        return couvertureReseaux.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/couverture/{id}")
    public ResponseEntity<Void> deleteCouvertureReseauById(@PathVariable Long id) {
        couvertureReseauxService.deleteCouvertureReseauxById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    private InfrastructureTypeService servicetype;

    @PostMapping("/Infrastructurestype")
    public Infrastructurestype addType(@RequestBody Infrastructurestype type) {
        return servicetype.addType(type);
    }

    @GetMapping("/Infrastructurestypes")
    public List<Infrastructurestype> getAllTypes() {
        return servicetype.getAllTypes();
    }
    @Autowired
    private ProgrammeInterventionService serviceprogramme;

    @PostMapping("/addIntervention")
    public ProgrammeIntervention addIntervention(@RequestBody ProgrammeIntervention intervention) {
        return serviceprogramme.addIntervention(intervention);
    }

    @GetMapping("/getAllInterventions")
    public List<ProgrammeIntervention> getAllInterventions() {
        return serviceprogramme.getAllInterventions();
    }

    @GetMapping("/total")
    public double calculateTotalCost() {
        return serviceprogramme.calculateTotalCost();
    }

}


