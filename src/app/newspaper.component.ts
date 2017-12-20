import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { NewspaperService } from './newspaper.service';
import { NewsPaper } from './NewsPaper';

@Component({
   selector: 'app-newspaper',
   templateUrl: './newspaper.component.html',
   styleUrls: ['./newspaper.component.css']
})
export class NewsPaperComponent implements OnInit {
   //Component properties
   allNewspapers: NewsPaper[];
   statusCode: number;
   requestProcessing = false;
   newspaperIdToUpdate = null;
   processValidation = false;
   //Create form
   newspaperForm = new FormGroup({
       name: new FormControl('', Validators.required),
       city: new FormControl('', Validators.required)
   });
   //Create constructor to get service instance
   constructor(private newspaperService: NewspaperService) {
   }
   //Create ngOnInit() and and load newspapers
   ngOnInit(): void {
	   this.getAllNewspapers();
   }
   //Fetch all newspapers
   getAllNewspapers() {
        this.newspaperService.getAllNewspapers()
	   .subscribe(
                data => this.allNewspapers = data,
                errorCode =>  this.statusCode = errorCode);
   }
   //Handle create and update newspaper
   onNewspaperFormSubmit() {
	  this.processValidation = true;
	  if (this.newspaperForm.invalid) {
	       return; //Validation failed, exit from method.
	  }
	  //Form is valid, now perform create or update
    this.preProcessConfigurations();
	  let name = this.newspaperForm.get('name').value.trim();
    let city = this.newspaperForm.get('city').value.trim();
	  if (this.newspaperIdToUpdate === null) {
	    //Handle create newspaper
	    let newspaper= new NewsPaper(null, name, city);
	    this.newspaperService.createNewspaper(newspaper)
	      .subscribe(successCode => {
		        this.statusCode = successCode;
			      this.getAllNewspapers();
			      this.backToCreateNewspaper();
			},
		        errorCode => this.statusCode = errorCode);
	  } else {
   	    //Handle update newspaper
	    let newspaper= new NewsPaper(this.newspaperIdToUpdate, name, city);
	    this.newspaperService.updateNewspaper(newspaper)
	      .subscribe(successCode => {
		        this.statusCode = successCode;
			      this.getAllNewspapers();
			      this.backToCreateNewspaper();
			},
		        errorCode => this.statusCode = errorCode);
	  }
   }
   //Load newspaper by id to edit
   loadNewspaperToEdit(newspaperId: string) {
      this.preProcessConfigurations();
      this.newspaperService.getNewspaperById(newspaperId)
	      .subscribe(newspaper => {
		      this.newspaperIdToUpdate = newspaper.newspaperId;
		      this.newspaperForm.setValue({ name: newspaper.name, city: newspaper.city });
			    this.processValidation = true;
			    this.requestProcessing = false;
		    },
		    errorCode =>  this.statusCode = errorCode);
   }
   //Delete newspaper
   deleteNewspaper(newspaperId: string) {
      this.preProcessConfigurations();
      this.newspaperService.deleteNewspaperById(newspaperId)
	      .subscribe(successCode => {
		      this.statusCode = successCode;
		      this.getAllNewspapers();
		      this.backToCreateNewspaper();
		   },
		   errorCode => this.statusCode = errorCode);
   }
   //Perform preliminary processing configurations
   preProcessConfigurations() {
          this.statusCode = null;
	  this.requestProcessing = true;
   }
   //Go back from update to create
   backToCreateNewspaper() {
          this.newspaperIdToUpdate = null;
          this.newspaperForm.reset();
	         this.processValidation = false;
   }
}
