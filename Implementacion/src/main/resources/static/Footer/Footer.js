class Footer extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
		<section class="footer">
			<div class="contenedor-base">
				<div class="imagenes-footer">
					<a href="https://twitter.com">
						<div class="img-footer twitter">
							<img src="../static/Footer/img/twitter.png" width="45" alt="Twitter @RescateDePatitas">
						</div>
					</a>
					<a href="https://www.facebook.com/">
						<div class="img-footer facebook">
							<img src="../static/Footer/img/fb.png" width="45" alt="Facebook @RescateDePatitas">
						</div>
					</a>
					<a href="https://mail.google.com/">
						<div class="img-footer gmail">
							<img src="../static/Footer/img/gmail.png" width="45" alt="Gmail RescateDePatitas@gmail.com">
						</div>
					</a>
				</div>
			</div>
		</section>
    `;
    }
}

customElements.define('main-footer', Footer);