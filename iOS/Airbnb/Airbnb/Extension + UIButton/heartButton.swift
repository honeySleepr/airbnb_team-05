//
//  heartButton.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

extension UIButton {

    func setImage(systemName: String) {
        contentHorizontalAlignment = .fill
        contentVerticalAlignment = .fill
        imageView?.contentMode = .scaleAspectFit
        setImage(UIImage(systemName: systemName), for: .normal)
    }
}
