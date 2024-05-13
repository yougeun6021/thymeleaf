package com.amudy.algorithm.controller;

import com.amudy.algorithm.domain.Level;
import com.amudy.algorithm.domain.ProblemSearchCond;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.algorithm.dto.SubmitDetailDto;
import com.amudy.algorithm.dto.UpdateProblemDto;
import com.amudy.algorithm.dto.UpdateSubmitDetailDto;
import com.amudy.algorithm.dto.UpdateSubmitProblemDto;
import com.amudy.algorithm.service.ProblemService;
import com.amudy.algorithm.service.SubmitService;
import com.amudy.global.dto.SelectBoxDto;
import com.amudy.global.response.ResData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlgorithmController {

    private final ProblemService problemService;
    private final SubmitService submitService;

    @GetMapping("/algorithm")
    public String algorithm(Model model){
        model.addAttribute("pbList",problemService.searchProblem(new ProblemSearchCond("t",1)));
        model.addAttribute("levels");
        model.addAttribute("pbSelectBox",problemService.selectBoxProblems(new SubmitSearchCond(null,"t",1)));
        model.addAttribute("subList",submitService.searchSubmit(new SubmitSearchCond(null,"t",1)));
        return "algorithm/algorithm";
    }

    @GetMapping("/algorithm/problem")
    public String searchProblem(ProblemSearchCond problemSearchcond, Model model){
        model.addAttribute("pbList",problemService.searchProblem(problemSearchcond));
        return "algorithm/algorithm :: pb_fragment";
    }


    @PostMapping("/algorithm/problem")
    public ResponseEntity<ResData<Long>> saveProblem(){
        return new ResponseEntity<>(new ResData<>(problemService.saveProblem()), HttpStatus.CREATED);
    }

    @PatchMapping("/algorithm/problem/{id}")
    public ResponseEntity<ResData<Long>> updateProblem(@PathVariable Long id, @RequestBody UpdateProblemDto updateProblemDto){
        return new ResponseEntity<>(new ResData<>(problemService.updateProblem(id,updateProblemDto)), HttpStatus.OK);
    }

    @DeleteMapping("/algorithm/problem")
    public ResponseEntity<Void> deleteProblem(@RequestBody List<Long> idList){
        problemService.deleteProblem(idList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/algorithm/submit")
    public ResponseEntity<ResData<Long>> saveSubmit(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(new ResData<>(submitService.saveSubmit(userDetails.getUsername())), HttpStatus.CREATED);
    }

    @PatchMapping("/algorithm/submit/{id}")
    public ResponseEntity<ResData<Long>> updateSubmitDetail(@PathVariable Long id, @RequestBody UpdateSubmitDetailDto updateSubmitDetailDto){
        return new ResponseEntity<>(new ResData<>(submitService.updateSubmitDetail(id, updateSubmitDetailDto)), HttpStatus.OK);
    }
    @PatchMapping("/algorithm/submit/{id}/problem")
    public ResponseEntity<ResData<Long>> updateSubmitProblem(@PathVariable Long id, @RequestBody UpdateSubmitProblemDto updateSubmitProblemDto){
        return new ResponseEntity<>(new ResData<>(submitService.updateSubmitProblem(id, updateSubmitProblemDto)), HttpStatus.OK);
    }

    @DeleteMapping("/algorithm/submit")
    public ResponseEntity<Void> deleteSubmit(@RequestBody List<Long> idList){
        submitService.deleteSubmit(idList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/algorithm/submit")
    public String searchSubmit(SubmitSearchCond submitSearchCond, Model model){
        model.addAttribute("subList",submitService.searchSubmit(submitSearchCond));
        model.addAttribute("pbSelectBox",problemService.selectBoxProblems(submitSearchCond));
        return "algorithm/algorithm :: sub_fragment";
    }

    @GetMapping("/algorithm/submit/{id}")
    public ResponseEntity<ResData<SubmitDetailDto>> submitDetail(@PathVariable Long id){
        return new ResponseEntity<>(new ResData<>(submitService.submitDetail(id)),HttpStatus.OK);
    }

    @GetMapping("/algorithm/submit/problem")
    public ResponseEntity<ResData<List<SelectBoxDto>>> getSelectBoxProblems(SubmitSearchCond submitSearchCond){
        return new ResponseEntity<>(new ResData<>(problemService.selectBoxProblems(submitSearchCond)),HttpStatus.OK);
    }

    @ModelAttribute("levels")
    private Level[] levels(){
        return Level.values();
    }
}
