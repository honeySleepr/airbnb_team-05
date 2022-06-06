//
//  InformationCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class InformationCell: UICollectionViewCell {
    
    static let identifier = "InformationCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        informationLayout()
    }
    
    
    
    
