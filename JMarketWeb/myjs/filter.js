!function ($) {
	$(document).ready(function () {
		// filtres
		$('#bt-trier').on('click', function() {

			$('#sort').css('display', 'none');
			$('.fleche-sort').addClass('fa');
			$('.fleche-sort').addClass('fa-angle-down');
			$('.fleche-sort').removeClass('fa-angle-up');
			
			$('#filters').stop().slideToggle(310, function() {
				if( $('#filters:visible').length )
					$('#bt-trier-mask:hidden').fadeIn();
				else {
					$('#bt-trier-mask:visible').fadeOut();
				}
			});
			if($('.fleche-filtre').hasClass('fa-angle-up')){
				$('.fleche-filtre').addClass('fa-angle-down');
				$('.fleche-filtre').removeClass('fa-angle-up');
			}
			else{
				$('.fleche-filtre').addClass('fa-angle-up');
				$('.fleche-filtre').removeClass('fa-angle-down');
			}
			return false;
		});

		$('#bt_sort').on('click', function() {
			$('#filters').css('display', 'none');
			$('.fleche-filtre').addClass('fa-angle-down');
			$('.fleche-filtre').removeClass('fa-angle-up');

			$('#sort').stop().slideToggle(310, function() {
				if( $('#sort:visible').length )
					$('#bt-trier-mask:hidden').fadeIn();
				else {
					$('#bt-trier-mask:visible').fadeOut();
				}
			});
			if($('.fleche-sort').hasClass('fa-angle-up')){
				$('.fleche-sort').addClass('fa-angle-down');
				$('.fleche-sort').removeClass('fa-angle-up');
			}
			else{
				$('.fleche-sort').addClass('fa-angle-up');
				$('.fleche-sort').removeClass('fa-angle-down');
			}
			return false;
		});

	});
}(window.jQuery);