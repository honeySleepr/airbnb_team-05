//
//  TumbnailCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class Tumbnail:UICollectionViewCell {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
        
    }
    
    private let tumbNailImage: UIImageView = {
        let tumbNailImage = UIImageView()
        return tumbNailImage
    }()
    
    private let superHost: UILabel = {
        let superHost = UILabel()
        let hostFont = UIFont(name: "Helvetica", size: 24)
        let superHostAttributedString = NSMutableAttributedString(string: "슈퍼호스트")
        superHost.attributedText = superHostAttributedString
        return superHost
    }()
    
    private let heart: UIButton = {
        let heart = UIButton()
        heart.setImage(systemName: "heart.fill")
        return heart
    }()
    
    
    
    
    
}
