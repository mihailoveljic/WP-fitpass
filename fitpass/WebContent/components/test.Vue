<div v-if="mode=='ADMINISTRATOR'">
	 <v-row>
	 <v-col cols="3" class="text-center">
	 
	 
	<template>
					<v-row justify="center">
						<v-dialog v-model="createDialog" persistent max-width="600px">
							<template v-slot:activator="{ on, attrs }">
								<v-btn v-bind="attrs" v-on="on" color="primary" class="ma-0 d-none d-lg-flex""
								 centered x-large>
									Add Buyer
								</v-btn>
							</template>
							<v-card>
								<v-card-title>
									<span class="text-h5">Create promo code</span>
								</v-card-title>
								<v-card-text>
									<v-container>
										<v-row>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="codeMark" label="Name*" required v-model="userRegistrationDTO.name"
												:rules="[() => !!userRegistrationDTO.name || 'This field is required!']" :error-messages="registrationErrorMessages">
												</v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<template>
												  <v-card flat color="transparent" >
												    <v-subheader>Min and max default slider</v-subheader>
												
												    <v-card-text>
												      <v-row>
												        <v-col class="pr-4">
												          <v-slider
												            v-model="slider"
												            class="align-center"
												            :max="max"
												            :min="min"
												            hide-details
												          >
												            <template v-slot:append>
												              <v-text-field
												                v-model="slider"
												                class="mt-0 pt-0"
												                hide-details
												                single-line
												                type="number"
												                style="width: 60px"
												              ></v-text-field>
												            </template>
												          </v-slider>
												        </v-col>
												      </v-row>
												    </v-card-text>
                                                </v-card>
</template>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<template>
													<v-container fluid>
														<p>Gender:</p>
														<v-radio-group ref="registrationGender" v-model="userRegistrationDTO.gender" mandatory
														 :rules="[() => !!userRegistrationDTO.gender || 'This field is required!']" :error-messages="registrationErrorMessages">
															<v-radio label="Male" value="MALE"></v-radio>
															<v-radio label="Female" value="FEMALE"></v-radio>
														</v-radio-group>
													</v-container>
												</template>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="registrationUsername" label="Username*" required v-model="userRegistrationDTO.username"
												  :rules="[  () => !!userRegistrationDTO.username || 'This field is required!',
												 			 checkIfUsernameIsUnique()  || 'Must be unique']" :error-messages="registrationErrorMessages"> <!--:class="{error : '!isUsernameUnique'}" v-on:blur="checkIfUsernameIsUnique"-->
												</v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="registrationPassword" label="Password" type="password" v-model="userRegistrationDTO.password"
													:rules="[() => !!userRegistrationDTO.password || 'This field is required!']" :error-messages="registrationErrorMessages"></v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<template>
													<div>
														<v-menu ref="menu" v-model="menu"
															:close-on-content-click="false"
															transition="scale-transition" offset-y min-width="auto">
															<template v-slot:activator="{ on, attrs }">
																<v-text-field v-model="expirationDate" label="Expiration date"
																	prepend-icon="mdi-calendar" readonly v-bind="attrs"
																	v-on="on">
																</v-text-field>
															</template>
															<v-date-picker ref="codeExpirationDate" v-model="expirationDate" required
																:active-picker.sync="activePicker"
																:min="Date.now()" @change="save"
																:rules="[() => !!expirationDate || 'This field is required!']" :error-messages="createErrorMessages"></v-date-picker>
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
	  <v-simple-table >
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
	        <tr
	          v-for="promoCode in promoCodes"
	          :key="promoCode.id"
	        >
	          <td>{{ promoCode.mark }}</td>
	          <td>{{ promoCode.expirationDate }}</td>
	          <td> {{ promoCode.discount }} </td>
	          <td> {{ promoCode.howManyTimesCanBeUsed }} </td>
	          <td><v-btn @click="deletePromoCode(promoCode)">delete</v-btn></td>
	        </tr>
	      </tbody>
	    </template>
	  </v-simple-table>
	  </v-col>
	  <v-col cols="3">
	  </v-col>
	 </v-row>
</div>