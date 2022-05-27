//
//  AdvertisingCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/26.
//

import UIKit

final class AdvertisingCell: UICollectionViewCell {
    
    static let identifier = "AdvertisingCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        advertisingLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
    }
    
    let mainExplain: UILabel = {
        let maintext = UILabel()
        maintext.numberOfLines = 0
        let font = UIFont(name: "Helvetica", size: 34)
        let attributedString = NSMutableAttributedString(string: "슬기로운\n자연생활")
        let length = attributedString.length
        attributedString.addAttributes([.foregroundColor:UIColor.black,
                                        .font:UIFont.systemFont(ofSize: 34, weight: .bold)],
                                       range: NSRange(location: 0, length: 9))
        maintext.attributedText = attributedString
        return maintext
    }()
    
    let mainImageView: UIImageView = {
        let mainImage = UIImageView()
        mainImage.image = UIImage(named: "mainImage")
        return mainImage
    }()
    
    let subExplain: UILabel = {
        let subText = UILabel()
        subText.numberOfLines = 0
        let subFont = UIFont(name: "Helvetica", size: 24)
        let subAttributedString = NSMutableAttributedString(string: "에어비앤비가 엄선한\n위시리스트를 만나보세요.")
        subAttributedString.addAttributes([.foregroundColor:UIColor.black,
                                        .font:UIFont.systemFont(ofSize: 24, weight: .bold)],
                                       range: NSRange(location: 0, length: 9))
        subText.attributedText = subAttributedString
        return subText
    }()
    
    let travelIdeaButton: UIButton = {
        let tempButton = UIButton()
        tempButton.translatesAutoresizingMaskIntoConstraints = false
        tempButton.setTitle("여행 아이디어 얻기", for: .normal)
        tempButton.setTitleColor(UIColor.white, for: .normal)
        tempButton.backgroundColor = .black
        tempButton.titleLabel?.font = UIFont.systemFont(ofSize: 17, weight: .bold)
        return tempButton
    }()
    
    func configure(image: UIImage?) {
        mainImageView.image = image
    }
    
    func appendSubView() {
        contentView.addSubview(mainImageView)
        contentView.addSubview(mainExplain)
        contentView.addSubview(subExplain)
        contentView.addSubview(travelIdeaButton)
    }
    
    func advertisingLayout() {
        mainImageView.translatesAutoresizingMaskIntoConstraints = false
        mainImageView.topAnchor.constraint(equalTo: contentView.topAnchor).isActive = true
        mainImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor).isActive = true
        mainImageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor).isActive = true
        mainImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor).isActive = true
        
        mainExplain.translatesAutoresizingMaskIntoConstraints = false
        mainExplain.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 24).isActive = true
        mainExplain.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16).isActive = true
        
        subExplain.translatesAutoresizingMaskIntoConstraints = false
        subExplain.topAnchor.constraint(equalTo: mainExplain.bottomAnchor, constant: 20).isActive = true
        subExplain.leadingAnchor.constraint(equalTo: mainExplain.leadingAnchor).isActive = true
        
        travelIdeaButton.translatesAutoresizingMaskIntoConstraints = false
        travelIdeaButton.topAnchor.constraint(equalTo: subExplain.bottomAnchor, constant: 20).isActive = true
        travelIdeaButton.leadingAnchor.constraint(equalTo: mainExplain.leadingAnchor).isActive = true
        
    }
}
