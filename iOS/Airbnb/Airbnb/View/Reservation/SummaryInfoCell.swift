//
//  SummaryInfoCell.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class SummaryInfoCell: UICollectionViewCell {
    
    static let identifier = "SummaryInfoCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        summaryInfoLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let summaryInfo: UILabel = {
       let summaryInfo = UILabel()
        summaryInfo.numberOfLines = 0
        summaryInfo.textColor = .systemGray
        summaryInfo.font = .systemFont(ofSize: 15)
        summaryInfo.text = "간략 정보"
        return summaryInfo
    }()
    
    private let hostName: UILabel = {
        let hostName = UILabel()
        hostName.numberOfLines = 0
        let hostNameAttributedString = NSMutableAttributedString(string: "호스트: Jong님")
        let length = hostNameAttributedString.length
        hostNameAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                                   .font: UIFont.systemFont(ofSize: 17)],
                                                  range: NSRange(location: 0, length: length))
        hostName.attributedText = hostNameAttributedString
        return hostName
    }()
    
    private let guestCount: UILabel = {
        let guestCount = UILabel()
        guestCount.numberOfLines = 0
        let guestCountAttributedString = NSMutableAttributedString(string: "집전체∙게스트 3명")
        let length = guestCountAttributedString.length
        guestCountAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                                   .font: UIFont.systemFont(ofSize: 17)],
                                                  range: NSRange(location: 0, length: length))
        guestCount.attributedText = guestCountAttributedString
        return guestCount
    }()
    
    private let totalPrice: UILabel = {
        let totalPrice = UILabel()
        totalPrice.numberOfLines = 0
        let totalPriceAttributedString = NSMutableAttributedString(string: "₩1,488,195")
        let length = totalPriceAttributedString.length
        totalPriceAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                                   .font: UIFont.systemFont(ofSize: 17)],
                                                  range: NSRange(location: 0, length: length))
        totalPrice.attributedText = totalPriceAttributedString
        return totalPrice
    }()
    
    func configure(name: String?, guestcnt: String?, price: String?) {
        hostName.text = name
        guestCount.text = guestcnt
        totalPrice.text = price
    }
    
    private func appendSubView() {
        contentView.addSubview(summaryInfo)
        contentView.addSubview(hostName)
        contentView.addSubview(guestCount)
        contentView.addSubview(totalPrice)
    }
    
    private func summaryInfoLayout() {
        summaryInfo.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            summaryInfo.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 12),
            summaryInfo.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            summaryInfo.heightAnchor.constraint(equalToConstant: 20)
        ])
        
        hostName.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            hostName.topAnchor.constraint(equalTo: summaryInfo.bottomAnchor, constant: 8),
            hostName.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            hostName.heightAnchor.constraint(equalToConstant: 22)
        ])
        
        guestCount.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            guestCount.topAnchor.constraint(equalTo: hostName.bottomAnchor, constant: 8),
            guestCount.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            guestCount.heightAnchor.constraint(equalToConstant: 22)
        ])
        
        totalPrice.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            totalPrice.topAnchor.constraint(equalTo: guestCount.bottomAnchor, constant: 8),
            totalPrice.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            totalPrice.heightAnchor.constraint(equalToConstant: 22),
            totalPrice.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -12)
        ])

    }
}
