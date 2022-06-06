//
//  ReservationViewController.swift
//  Airbnb
//
//  Created by 이건행 on 2022/05/24.
//

import UIKit

class ReservationViewController:UIViewController {
    
    private var reservationView = ReservationView()
    private var datasource = ReservationDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view = reservationView
        reservationView.setDataSource(datasource)
        
    }
}
