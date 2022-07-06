Vue.component("promo-codes", {
	name: "promo-codes",
	data: function () {
		    return {
				promoCodes : [],
				createDialog:false,
				promoCodeDTO: {
					mark: null,
					expirationDate: null,
					howManyTimeCanBeUsed: null,
					discount: 0
					},
				activePicker: null,
				expirationDate: null,
				menu: false,
		        createErrorMessages : "",
		        createFormHasErrors : false,				
			}
	},
	props:
		['userToken']
	,
	template: 
` <div :v-if="userToken.mode=='ADMINISTRATOR'">
<v-row>
	<v-col cols="3" class="text-center">


		<template>
			<v-row justify="center">
				<v-dialog v-model="createDialog" persistent max-width="600px">
					<template v-slot:activator="{ on, attrs }">
						<v-btn v-bind="attrs" v-on="on" color="primary" class="ma-0 d-none d-lg-flex""
							centered x-large>
							   Create promo code
						   </v-btn>
					   </template>
					   <v-card>
						   <v-card-title>
							   <span class=" text-h5">Create promo code</span>
							</v-card-title>
							<v-card-text>
								<v-container>
									<v-row>
										<v-col cols="12" sm="8" md="12">
											<v-text-field ref="codeMark" label="Code*" required
												v-model="promoCodeDTO.mark"
												:rules="[() => !!promoCodeDTO.mark || 'This field is required!']"
												:error-messages="createErrorMessages">
											</v-text-field>
										</v-col>
										<v-col cols="12" sm="8" md="12">
											<template>
												<v-card flat color="transparent">
													<v-subheader>Discount</v-subheader>
													<v-card-text>
														<v-row>
															<v-col class="pr-4">
																<v-slider v-model="promoCodeDTO.discount" class="align-center"
																	max="100" min="0" hide-details>
																	<template v-slot:append>
																		<v-text-field
																			v-model="promoCodeDTO.discount" ref="codeDiscount"
																			class="mt-0 pt-0" hide-details
																			single-line type="number"
																			style="width: 60px"></v-text-field>
																	</template>
																</v-slider>
															</v-col>
														</v-row>
													</v-card-text>
												</v-card>
											</template>
										</v-col>
										<v-col cols="12" sm="8" md="12">
											<v-text-field v-model="promoCodeDTO.howManyTimeCanBeUsed" ref="codeHowManyTimesCanBeUsed"
												:rules="[() => !isNaN(promoCodeDTO.howManyTimeCanBeUsed) || 'This field is required!']"
												:error-messages="createErrorMessages" hide-details single-line
												type="number" />
										</v-col>
										<v-col cols="12" sm="8" md="12">
											<template>
												<div>
													<v-menu ref="menu" v-model="menu"
														:close-on-content-click="false"
														transition="scale-transition" offset-y min-width="auto">
														<template v-slot:activator="{ on, attrs }">
															<v-text-field v-model="expirationDate"
																label="Expiration date" prepend-icon="mdi-calendar"
																readonly v-bind="attrs" v-on="on">
															</v-text-field>
														</template>
														<v-date-picker ref="codeExpirationDate"
															v-model="expirationDate" required
															:active-picker.sync="activePicker"
															@change="save"
															:rules="[() => !!expirationDate || 'This field is required!']"
															:error-messages="createErrorMessages"></v-date-picker>
													</v-menu>
												</div>

											</template>
										</v-col>
									</v-row>
								</v-container>
								<small>*indicates required field</small>
							</v-card-text>
							<v-card-actions>
								<v-spacer></v-spacer>
								<v-btn color="blue darken-1" text @click="createDialog = false">
									Cancel
								</v-btn>
								<v-btn color="blue darken-1" text @click="createPromoCode()">
									Create
								</v-btn>
							</v-card-actions>
							</v-card>
				</v-dialog>
			</v-row>
		</template>
	</v-col>
	<v-col cols="6">
		<v-simple-table>
			<template v-slot:default>
				<thead>
					<tr>
						<th class="text-left text-h6 flex-row-reverse">
							Code
						</th>
						<th class="text-left text-h6 flex-row-reverse">
							Expiration Date
						</th>
						<th class="text-left text-h6 flex-row-reverse">
							Discount
						</th>
						<th class="text-left text-h6">
							Remaining usages
						</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="promoCode in promoCodes" :key="promoCode.id">
						<td>{{ promoCode.mark }}</td>
						<td>{{ promoCode.day }}.{{ promoCode.month }}.{{ promoCode.year }}</td>
						<td> {{ promoCode.discount }} </td>
						<td> {{ promoCode.howManyTimeCanBeUsed }} </td>
						<td>
							<v-btn @click="deletePromoCode(promoCode)">delete</v-btn>
						</td>
					</tr>
				</tbody>
			</template>
		</v-simple-table>
	</v-col>
	<v-col cols="3">
	</v-col>
</v-row>
</div>
`
	,
	methods : {
		save(date) {
				this.$refs.menu.save(date);
		},
		deletePromoCode(promoCode){
			axios.delete('rest/PromoCodeController/' + promoCode.id)
              .then(response => {
					if(response.data == false){
						alert("Failed to delete promo code!");
						return false;
					} 
					this.reloadData();
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		},
		createPromoCode(){			
			this.promoCodeDTO.expirationDate = new Date(this.expirationDate);
			axios.post('rest/PromoCodeController', this.promoCodeDTO)
              .then(response => {
					if(response.data == null){
						alert("Failed to create promo code!");
						return false;
					} 
					this.createDialog = false;
					this.reloadData();
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		},
		reloadData(){
			axios.get('rest/PromoCodeController')
              .then(response => {
					this.promoCodes = response.data;
					this.promoCodes.forEach(promoCode =>{
									let date = new Date(promoCode.expirationDate);
									promoCode.day = date.getDate();
									promoCode.month = date.getMonth();
									promoCode.year = date.getYear();
								});
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		}
	},
	computed: {
		codeForm () {
	        return {
	         	codeMark: this.promoCodeDTO.mark,
				codeDiscound:this.promoCodeDTO.discount,
				codeHowManyTimesCanBeUsed:this.promoCodeDTO.howManyTimeCanBeUsed,
				codeExpirationDate: this.promoCodeDTO.expirationDate,
			}
        }
	},
	watch: {
		menu(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		},
	},
	mounted () {
              axios.get('rest/PromoCodeController')
              .then(response => {
					this.promoCodes = response.data;
					this.promoCodes.forEach(promoCode =>{
									let date = new Date(promoCode.expirationDate);
									promoCode.day = date.getDate();
									promoCode.month = date.getMonth() + 1;
									promoCode.year = date.getYear() + 1900;
								});
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
    },
	created(){
		if(!this.userToken){
			this.$router.push('/');
			}
	},
	beforeUpdate(){
		if(!this.userToken){
			this.$router.push('/');
			}
	}
});